# spring-cloud-alibaba
Spring Cloud Alibaba学习demo（复习巩固）

关于：
 - SpringBoot自动配置（druid,redis等）
 - mybatis-plus使用（代码自动生成、crud接口）
 - restful风格api用法
 - nacos服务注册与发现
 - 服务间feign远程调用，及负载均衡
 - 待更新···

[TOC]

# 基于Spring Cloud Alibaba构建一个微服务

## 微服务方案简介

目前市场上主流的微服务有三套：

- 第一套：Spring Boot + Spring Cloud Netflix
- 第二套：Spring Boot + Dubbo + Zookeeper
- 第三套：Spring Boot + Spring Cloud Alibaba

第一套方案是目前使用较为广泛的，但是**2018 年 12 月 12 日，Netflix 宣布 Spring Cloud Netflix 系列技术栈进入维护模式（不再添加新特性）**，这意味着该项目不再拥有功能上的更新，于是其中很多的模块就出现了相应的替代品，也许在不久的将来，整个就会被淘汰。

第二套的特点是集成了Apache Dubbo这个高性能、轻量级的Java RPC开源框架，相信Dubbo现在已经非常流行，使用过的人应该都能感受到他的强大。其次这套方案协调框架采用的是Zookeeper，这同样是在分布式领域非常流行的一个强一致性框架，解决了分布式环境中复杂的协调和管理服务，让开发人员专注于核心应用程序逻辑，而不必担心应用程序的分布式特性。

第三套方案也是最年轻的一套方案，**2018 年 10 月 31 日的凌晨，这个伟大的日子里，Spring Cloud Alibaba 正式入驻了 Spring Cloud 官方孵化器，并在 Maven 中央库发布了第一个版本：** 

[Spring Cloud for Alibaba 0.2.0 released](https://spring.io/blog/2018/10/30/spring-cloud-for-alibaba-0-2-0-released)

如下是官方的介绍：

> Spring Cloud Alibaba 致力于提供微服务开发的一站式解决方案。此项目包含开发分布式应用微服务的必需组件，方便开发者通过 Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务。
>
> 依托 Spring Cloud Alibaba，您只需要添加一些注解和少量配置，就可以将 Spring Cloud 应用接入阿里微服务解决方案，通过阿里中间件来迅速搭建分布式应用系统。

**在2019的8月份，Spring Cloud Alibaba 才发布了他的第一个正式版本**，正如他所介绍的那样，致力于一站式解决方案，你只需要几个简单的配置，就可以解决微服务中需要解决的诸多问题：服务的注册与发现，服务间通信，负载均衡，限流降级，动态配置等。

## Spring Cloud Alibaba

### 服务注册与发现

阿里采用了全新的Nacos来实现服务注册中心，相比于传统的Eureka，更加简单易用，功能也似乎更加强大，使用简单，界面UI也十分友好，而Nacos的分布式协调架构放弃了Zookeeper，至于为什么放弃，解释如下：

> ### CAP
>
> 有个思考，从 CAP 角度考虑，服务注册中心是 CP 系统还是 AP 系统呢？
>
> - 服务注册中心是为了服务间调用服务的，那么绝对不允许因为服务注册中心出现了问题而导致服务间的调用出问题
> - 假如有 node1，node2，node3 集群节点。保存着可用服务列表 ip1，ip2，ip3，试想如果此时不一致，比如 node1 只保存了ip1，ip2，此时服务读取 node1 的节点，那么会造成什么影响？
>
> 调用 node1 的服务，顶多就是负载均衡时不会有流量打到 ip3，然后等 node1 同步回 ip3 后，又一致了，这对服务其实没什么太大影响。所以，推测出服务注册中心应该是个 AP 系统。
>
> ### Zookeeper 是个 CP 系统，强一致性
>
> - 场景1，当 master 挂了，此时 Zookeeper 集群需要重新选举，而此时服务需要来读取可用服务，是不可用的。影响到了服务的可用性当然你可以说服务本地有缓存可用列表。然而下面这种方式就更无法处理了。
> - 场景2，分区可用。试想，有 3 个机房，如果其中机房 3 和机房 1，2 网络断了，那么机房 3 的注册中心就不能注册新的机器了，这显然也不合理
>
> ![](https://mew.oss-cn-shanghai.aliyuncs.com/img2/da288a836eac2ddeeb0bbdfa0fd29fb4b8d.jpg)
>
> - 从健康检查角度来看Zookeeper 是通过 TCP 的心跳判断服务是否可用，但 TCP 的活性并不代表服务是可用的，如：连接池已满，DB 挂了等
>
> ### 理想的注册中心
>
> - 服务自动注册发现。最好有新的服务注册上去时还能推送到调用端
> - 能对注册上来的机器方便的进行管理，能手动删除（发送信号让服务优雅下线）、恢复机器
> - 服务的健康检查，能真正的检测到服务是否可用
> - 可以看到是否有其他调用服务正在订阅注册上来的服务
> - 能够带上些除了 IP 外的其它信息

关于Nacos的使用方法，请戳[这里]()跳转,推荐使用Docker方式安装。

### 服务间通信

Spring Cloud Alibaba仍然支持对于Dubbo的集成，对代码基本无侵入，同时，也可以使用Feign调用，他底层不同于Dubbo，封装了Ribbon，属于http调用，Spring Cloud自带，使用起来非常便捷：

```java
@FeignClient("service1")
public interface IGetUserService {
    @GetMapping("/get")
    List<User> feignGet();
}
```

调用以上接口就相当于以http的方式调用”service1“服务的 "/get" 端点。同时，当有多个“service1”存在时，Feign自动的就实现了负载均衡，默认情况下使用的是轮询访问策略，当然还有其他策略可以选择，直接在yaml文件里配置就可以生效：

```yaml
# 配置负载均衡，默通过ribbon实现，默认是轮询模式
sca-provider-service: # 单个服务写上服务名
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule # 随机规则
#   NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RoundRobinRule # 轮询(默认)
#   NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RetryRule # 重试
#   NFLoadBalancerRuleClassName: com.netflix.loadbalancer.WeightedResponseTimeRule # 响应时间权重
```

### 服务熔断降级

为什么要做熔断降级，首先了解“雪崩”效应:

> 在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以通过 `RPC` 相互调用，在 Spring Cloud 中可以用 `RestTemplate + LoadBalanceClient` 和 `Feign` 来调用。为了保证其高可用，单个服务通常会集群部署。由于网络原因或者自身的原因，服务并不能保证 100% 可用，如果单个服务出现问题，调用这个服务就会出现线程阻塞，此时若有大量的请求涌入，`Servlet` 容器的线程资源会被消耗完毕，导致服务瘫痪。服务与服务之间的依赖性，故障会传播，会对整个微服务系统造成灾难性的严重后果，这就是服务故障的 **“雪崩”** 效应。

阿里使用**[Sentinel](https://github.com/alibaba/Sentinel/wiki/介绍)** (👈🏻点击进入中文文档)来做熔断降级

### 路由网关统一访问接口

此处沿用Spring Cloud Gateway

#### Spring Cloud Gateway 功能特征

> - 基于 Spring Framework 5，Project Reactor 和 Spring Boot 2.0
> - 动态路由
> - Predicates 和 Filters 作用于特定路由
> - 集成 Hystrix 断路器
> - 集成 Spring Cloud DiscoveryClient
> - 易于编写的 Predicates 和 Filters
> - 限流
> - 路径重写

#### Spring Cloud Gateway 工程流程

![](https://mew.oss-cn-shanghai.aliyuncs.com/img2/22e4eccf2cbe09332678c04b8de98ebe.jpg)

客户端向 Spring Cloud Gateway 发出请求。然后在 Gateway Handler Mapping 中找到与请求相匹配的路由，将其发送到 Gateway Web Handler。Handler 再通过指定的过滤器链来将请求发送到我们实际的服务执行业务逻辑，然后返回。

过滤器之间用虚线分开是因为过滤器可能会在发送代理请求之前（`pre`）或之后（`post`）执行业务逻辑。

### 链路追踪

- SkyWalking

### 异步通信

- RocketMQ
- RibbitMQ
- Kafka

