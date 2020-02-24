> 转载请注明作者及出处：
> https://blog.csdn.net/liuminglei1987/article/details/104047691
> 本文出自[银河架构师](https://blog.csdn.net/liuminglei1987)的博客。

截止到2020年春节前，总算是把Spring Cloud微服务架构落地应用第一阶段写完了。十几篇文章，查找起来也不太好找，于是，也是时候写一个汇总文章了，提供统一入口，方便查找与阅读。

在此系列开篇便已提过，当前系列是以Spring Boot 2.1.7.RELEASE，Spring Cloud Greenwich.SR2，Spring Cloud Alibaba 2.1.0.RELEASE为框架基础，后续均以此为版本参照。也可能在本系列大致完结之后，出以Spring Cloud Finchley、Spring Bboot 2.0.x.RELEASE及其它版本为框架基础的系列文章，请期待吧！

关于注册中心和配置中心，为何不用Eureka和 Spring Cloud Config？没有刻意不采用Eureka和Spring Cloud Config，而是在比较了nacos和consul之后，这俩均能同时替换掉Eureka和Spring Cloud Config，太刺激了，何乐而不为呢？加之我们在国内，那“最好”的组件便是nacos了，也算是国产化了吧，同时也更符合国内开发者习惯。另外，阿里什么实力，毋庸置疑。不过，后续有时间，也会出Eureka、Spring Cloud Config、Consul、Spring Cloud Consul相关的文章。

为啥费劲写这些文章？一是为了巩固自己所学的知识，写下来，远比搞清楚要复杂的多！二是为了写出来，避免大家踩同样的坑，与大家共同分享。三是虚心接受大家的批评指教，共同提高。有任何问题，可随时联系我： liuminglei200@163.com。

关于源码？gitee：[https://gitee.com/xbd521/SpringCloudLearning](https://gitee.com/xbd521/SpringCloudLearning)，github：[https://github.com/liuminglei/SpringCloudLearning](https://github.com/liuminglei/SpringCloudLearning)，不要忘了start哦！！！

**文章汇总**：[Spring Cloud进阶之路：汇总篇](https://blog.csdn.net/liuminglei1987/article/details/104047691)

### Spring Cloud 进阶之路系列

Greenwich版本：

Spring Cloud Greenwich.SR2, Spring Boot 2.1.7.RELEASE, Spring Cloud Alibab 2.1.0.RELEASE.

* [Spring Cloud进阶之路 | 一：服务注册与发现（nacos）](https://blog.csdn.net/liuminglei1987/article/details/103617481)
* [Spring Cloud进阶之路 | 二：服务提供者（discovery）](https://blog.csdn.net/liuminglei1987/article/details/103632478)
* [Spring Cloud进阶之路 | 三：服务消费者（rest+ribbon）](https://blog.csdn.net/liuminglei1987/article/details/103700975)
* [Spring Cloud进阶之路 | 四：服务消费者（feign）](https://blog.csdn.net/liuminglei1987/article/details/103744990)
* [Spring Cloud进阶之路 | 五：配置中心（nacos）](https://blog.csdn.net/liuminglei1987/article/details/103814368)
* [Spring Cloud进阶之路 | 六：断路器（hystrix）](https://blog.csdn.net/liuminglei1987/article/details/103814441)
* [Spring Cloud进阶之路 | 七：服务网关（zuul）](https://blog.csdn.net/liuminglei1987/article/details/104003890)
* [Spring Cloud进阶之路 | 八：授权服务（Spring Cloud Oauth2）](https://blog.csdn.net/liuminglei1987/article/details/104004034)
* [Spring Cloud进阶之路 | 九：资源服务（Spring Cloud Oauth2）](https://blog.csdn.net/liuminglei1987/article/details/104004223)
* [Spring Cloud进阶之路 | 十：服务网关整合安全框架（zuul+ Spring Cloud Oauth2）](https://blog.csdn.net/liuminglei1987/article/details/104004425)
* [Spring Cloud进阶之路 | 十一：断路器监控（Hystrix Dashboard）](https://blog.csdn.net/liuminglei1987/article/details/104004613)
* [Spring Cloud进阶之路 | 十二：断路器聚合监控（Turbine）](https://blog.csdn.net/liuminglei1987/article/details/104004747)
* [Spring Cloud进阶之路 | 十三：服务链路追踪（Spring Cloud Sleuth）](https://blog.csdn.net/liuminglei1987/article/details/104004884)
* [Spring Cloud进阶之路 | 十四：服务网关重构（FactoryBean、动态配置、token解析、user传递）](https://blog.csdn.net/liuminglei1987/article/details/104199450)
* [Spring Cloud进阶之路 | 十五：服务网关集成断路器（zuul + hystrix）](https://blog.csdn.net/liuminglei1987/article/details/104226711)
* [Spring Cloud进阶之路 | 十六：服务网关集成断路器监控（zuul + Hystrix Dashboard）](https://mp.csdn.net/postedit/104226757)
* [Spring Cloud进阶之路 | 十七：服务网关性能调优（zuul）](https://blog.csdn.net/liuminglei1987/article/details/104282000)
* [Spring Cloud进阶之路 | 十八：授权服务（Spring Cloud Oauth2）ClientDetailsService之JdbcClientDetailsService](https://blog.csdn.net/liuminglei1987/article/details/104363638)
* [Spring Cloud进阶之路 | 十九：授权服务（Spring Cloud Oauth2）TokenStore之JdbcTokenStore](https://blog.csdn.net/liuminglei1987/article/details/104371385)
* [Spring Cloud进阶之路 | 二十：授权服务（Spring Cloud Oauth2）TokenStore之RedisTokenStore](https://blog.csdn.net/liuminglei1987/article/details/104396970)
* [Spring Cloud进阶之路 | 二十一：授权服务（Spring Cloud Oauth2）JWT实现-TokenStore之JwtTokenStore](https://blog.csdn.net/liuminglei1987/article/details/104471726)
* [Spring Cloud进阶之路 | 二十二：资源服务（Spring Cloud Oauth2）JWT实现](https://blog.csdn.net/liuminglei1987/article/details/104471814)
* 未完待续...


### 与Spring Cloud有关的避坑指南

* [避坑指南（〇）：ribbon超时配置不生效](https://blog.csdn.net/liuminglei1987/article/details/103676945)
* [避坑指南（一）：Spring Security Oauth2中refresh_token刷新access_token异常](https://blog.csdn.net/liuminglei1987/article/details/103763106)
* [避坑指南（二）：hystrix.stream端点404问题](https://blog.csdn.net/liuminglei1987/article/details/103891303)
* [避坑指南（三）：Spring Security Oauth2框架如何初始化AuthenticationManager](https://blog.csdn.net/liuminglei1987/article/details/103963070)
* [避坑指南（四）：zuul整合断路器监控线程池一直loading](https://blog.csdn.net/liuminglei1987/article/details/104041348)
* [避坑指南（五）：Hystrix断路器监控指标详解](https://blog.csdn.net/liuminglei1987/article/details/104040958)
* [避坑指南（六）：Hystrix超时时间小于ribbon超时时间报错](https://blog.csdn.net/liuminglei1987/article/details/104200256)
* [避坑指南（七）：Spring Cloud Oauth2配置JwtTokenStore后不生效，依然生成普通token](https://blog.csdn.net/liuminglei1987/article/details/104414508)
* [避坑指南（八）：Spring Cloud Oauth2 token_key端点404](https://blog.csdn.net/liuminglei1987/article/details/104471775)

### 后续

* 这个系列会持续更新，敬请期待

### 支持我

笔者开通了个人微信公众号【银河架构师】，分享工作、生活过程中的心得体会，填坑指南，技术感悟等内容，会比博客提前更新，欢迎订阅。

![@银河架构师](https://img-blog.csdnimg.cn/20200120104422781.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xpdW1pbmdsZWkxOTg3,size_16,color_FFFFFF,t_70)






