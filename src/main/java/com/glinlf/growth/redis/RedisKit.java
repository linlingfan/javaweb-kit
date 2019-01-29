package com.glinlf.growth.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author created by com.glinlf
 * @date 2018/7/24
 * @remark redis工具类
 */
@Component
public class RedisKit {

    private static Logger LOG = LoggerFactory.getLogger(RedisKit.class);

    private ThreadLocal<String> lockFlag = new ThreadLocal<String>();

    private static String NX = "NX"; // 将key 的值设为value ，当且仅当key 不存在，等效于 SETNX
    private static String PX = "PX"; // 已毫秒为单位 过期时间

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    //------redis lock------------

    /**
     * 获取当前锁 存在redis中（如果已存在锁 其他县城无法获得锁）
     * 保证设置锁 和 过期时间的原子性
     *
     * @param key
     * @param value  锁资源名 配置UUID 标识当前线程锁
     * @param expire 毫秒
     */
    public boolean setRedisLock(String key, String lockValue, long expire) {

        try {
            String result = redisTemplate.execute(new RedisCallback<String>() {

                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    Object nativeConnection = connection.getNativeConnection();
                    String lockName = lockValue + "_" + UUID.randomUUID().toString();
                    lockFlag.set(lockName);
                    // 集群模式
                    if (nativeConnection instanceof JedisCluster) {
                        return ((JedisCluster) nativeConnection).set(key, lockName, NX, PX, expire);
                    }
                    // 单机模式
                    if (nativeConnection instanceof Jedis) {
                        return ((Jedis) nativeConnection).set(key, lockName, NX, PX, expire);
                    }
                    return null;
                }
            });
            LOG.info("获取锁: {} 的时间：{}", result, new Date());
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            LOG.error("set redis lock occured an Exception", e);
        }
        return false;
    }

    /**
     * 建议 过期时间大于执行时间
     * 如果执行时间大于过期时间 可能被另一个线程2持有了锁，会发生线程1释放了线程2的锁
     * 解决方法 每次获取锁时 生成一个随机不唯一的串放入当前线程中，然后再放入 Redis。释放锁作比较判断
     * (如果要保证 读删的原子性 建议使用发送脚本执行)
     *
     * @param key
     */
    public void releaseLock(String key) {
        Object value = get(key);
        if (value != null) {
            if (Objects.equals(String.valueOf(value), lockFlag.get())) {
                redisTemplate.delete(key);
            }
        }
    }

    /**
     * 指定缓存失效时间
     *
     * @param key
     * @param time 毫秒
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
            }
            return true;
        } catch (Exception e) {
            LOG.error("redis operation error ex:{}" + e.getMessage(), e);
            return false;
        }
    }

    /**
     * 获取过期时间
     *
     * @param key not null
     * @return 单位毫秒秒
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {

        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            LOG.error("redis operation error ex:{}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param keys 一个或多个key
     */
    public void del(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

//------------String----------------

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通set值
     *
     * @param key
     * @param value
     * @return true成功 false失败
     */
    public boolean put(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            LOG.error("redis operation error ex:{}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 设置过期时间set
     *
     * @param key
     * @param value
     * @param time  时间小于0则不限期 毫秒
     * @return true成功 false失败
     */
    public boolean put(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
            } else {
                put(key, value);
            }
            return true;
        } catch (Exception e) {
            LOG.error("redis operation error ex:{}", e.getMessage(), e);
            return false;
        }
    }

//-----------------hash-------------------

    /**
     * hashGet
     *
     * @param key  not null
     * @param item not null
     * @return
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hPut(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            LOG.error("redis operation error ex:{}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  过期时间 秒（如有时间将覆盖）
     * @return
     */
    public boolean hPut(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LOG.error("redis operation error ex:{}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除hash表中的某个item-value值
     *
     * @param key
     * @param item
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 清空hash表下的所有key-value
     *
     * @param key 键（表）
     */
    public void hEmpty(String key) {
        Set<String> items = redisTemplate.keys(key);
        items.stream().forEach(item -> {
            hdel(key, item);
        });

    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    // ----Hash Map------

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key
     * @param map
     * @return true success false failure
     */
    public boolean hmPut(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            LOG.error("redis operation error ex:{}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * HashSet 设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmPut(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LOG.error("redis operation error ex:{}", e.getMessage(), e);
            return false;
        }
    }

//    -------------list---------------

    /**
     * list 往list头push值
     *
     * @param key
     * @param item
     * @param time 过期时间 毫秒 -1永不超时
     * @return
     */
    public boolean lPush(String key, Object item, long time) {
        try {
            redisTemplate.opsForList().leftPush(key, item);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LOG.error("redis operation error ex:{}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 从最后push值
     *
     * @param key
     * @param item
     * @param time 过期时间 秒
     * @return
     */
    public boolean rPush(String key, Object item, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, item);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LOG.error("redis operation error ex:{}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * get 队列头（第一个值）的值
     *
     * @param key
     * @return
     */
    public Object lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 获取队列头（第一个值） 没有则等待
     *
     * @param key
     * @param timeOut 等待超时时间 毫秒
     * @return
     */
    public Object blPop(String key, long timeOut) {
        return redisTemplate.opsForList().leftPop(key, timeOut, TimeUnit.MILLISECONDS);
    }

    /**
     * get 队列头（第一个值）的值
     *
     * @param key
     * @return
     */
    public Object rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 获取队列尾（第一个值） 没有则等待
     *
     * @param key
     * @param timeOut 等待超时时间 毫秒
     * @return
     */
    public Object brPop(String key, long timeOut) {
        return redisTemplate.opsForList().rightPop(key, timeOut, TimeUnit.MILLISECONDS);
    }

    /**
     * list的大小
     *
     * @param key
     * @return
     */
    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * list 切片 弹出队列范围数据
     *
     * @param key
     * @param start
     * @param end
     */
    public void lTrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 弹出某范围的元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> lRang(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 从头部批量推入队列
     *
     * @param key
     * @param items
     */
    public void lPushAll(String key, Collection items) {
        redisTemplate.opsForList().leftPushAll(key, items);
    }

    /**
     * 从尾部批量推入
     *
     * @param key
     * @param items
     */
    public void rPushAll(String key, Collection items) {
        redisTemplate.opsForList().rightPushAll(key, items);
    }

}
