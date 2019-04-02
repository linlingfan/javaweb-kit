# javaweb-kit
some tools  about javaweb

1. springboot 整合 websocket
    考虑聊天系统设计？TODO
2. 接口自定义访问权限 工具类 AuthKit RestUtils
    接口权限的其他验证规则？Basic + token？
    
3. 读取CSV文件工具类 CSVKit 

   [jar 推荐：uniVocity-parsers：速度最快功能最全的CSV开发库之一](http://hao.jobbole.com/univocity-parsers/)
   
4. 日期格式化等工具类 DateKit
    Java11 时间相关类似乎有优化？TODO...
5. Id生成 IdKit
    分布式ID生成策略？不要使用UUID... 
        1. 并发低还是自增长。
        2. 时间戳+时间业务标识
        3. Facebook 的算法。机房id+实例Id + 啥的？？？...
6. Json工具类jackJson JsonHelper
7. 小程序二维码生产类 MiniAppQrCode
8. spring bean 获取工具类 SpringKit。
9. 日志切面配置模板
10. 全局异常配置模板
11. redis配置工具类 RedisKit
12. Okhttp相关工具类 OkhttpKit
13. Swagger2相关配置
14. 链式集合类等
15. Logback系统错误日志邮箱提醒配置

### 项目经历
    注意：对接第三方活动。虚拟商城？
         退换货设计？
         消息推送设计
         定时任务设计
         系统拆分设计
         数据同步设计（公众号用户信息）设计多线程，springboot的异步调用 ，java8 的并行流
         多平台登录接口设计
         公众号后台管理相关开发
         问题订单同步记录处理
         
            