package com.glinlf.growth.redis;


import com.glinlf.growth.NoRollbackTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author created by com.glinlf
 * @date 2019/1/29
 * @remark
 */
public class redisKitTest extends NoRollbackTester {

    @Autowired
    RedisKit redisKit;

    @Test
    public void test() {

    }
    static final String testKey = "test-key";


    @Test
    public void testRedisKit() {
        redisKit.hPut(testKey, "glinlf","hello redis");

        System.out.println(redisKit.hGet(testKey, "glinlf"));
    }
}
