# mybatis generator (MBG)的用法
[MyBatis Generator 配置文件详解](https://www.jianshu.com/p/2cace13b7819)

### Mybatis generator 踩坑
- 重复使用generator生成mapper.xml时，mapper.xml的  
文件并不是每次都覆盖原文件，而是在源文件中追加新内容  
导致项目报错  
--原因：MBG依赖版本过低  
--解决办法：使用最新的依赖版本,在generatorConfig.xml文件中添加  
    生成mapper.xml时覆盖原文件的配置
  ```maven
  <plugin type="org.mybatis.generator.plugins.UnmergeableXmlMappersPlugin"/>
  ```
  
 ### 
 [Spring Security做JWT认证和授权](https://www.jianshu.com/p/d5ce890c67f7)
 
 ### Spring Security 踩坑
 - 在配置继承`WebSecurityConfigurerAdapter`的自定义类时，  
 除了加上`@EnableWebSecurity`开启安全认证模块时，还需要加上  
 `EnableGlobalMethodSecurity(prePostEnabled = true)`注解，  
 否则在`controller`层请求接口前的权限认证`@PreAuthorize`会不生效
 
 ### spring aop  
 [Spring4深入理解AOP02----AOP简介，AspectJ，AOP基于注解和XML配置（5种通知，切面优先级）](https://blog.csdn.net/ochangwen/article/details/52557724)  
 [org.aspectj.lang.JoinPoint-中文简要API](https://blog.csdn.net/a9529lty/article/details/7031070)
 
 ### RabbitMQ
 [RabbitMQ教程](https://blog.csdn.net/hellozpc/article/details/81436980#52_204)  
 [RabbitMQ技术详解](http://www.uml.org.cn/zjjs/201805234.asp)  
 [Spring Boot 实现 RabbitMQ 延迟消费和延迟重试队列](https://www.cnblogs.com/xishuai/p/spring-boot-rabbitmq-delay-queue.html)  
 [RAbbitMQ中文文档](http://rabbitmq.mr-ping.com/)
 
 #### RabbitMQ踩坑
 - 使用`TTL`和`DLX`实现延时队列，生产者延时消息发出后，消费者接收不到消息  
   原因：生产者发送的消息参数类型与消费者接收的消息参数类型不一致；
 
 
 ### 简单使用elasticsearch
 [SpringBoot整合Elasticsearch](https://blog.csdn.net/chen_2890/article/details/83895646)
 - 踩坑  
  [关于ElasticSearch的9200和9300端口区别](https://blog.csdn.net/a992795427/article/details/86611650)
 
 
 ### 小知识点
 `@Order(1)`主要用来控制配置类的加载顺序
