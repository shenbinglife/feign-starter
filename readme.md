# Feign Starter
Feign是一个声明式的Web服务客户端，使用Feign可使得Web服务客户端的写入更加方便。  
Spring官方提供了*Spring Cloud Starter Feign*，将Feign与SpringBoot结合，但是通常情况下，一个简单的springboot项目并不需要SpringCloud支持，所以，本人基于Spring Cloud Starter Feign的源码，抽取出了Feign Starter项目。

使用Feign Starter，可以让Feign与SpringBoot更容易的结合使用，并且支持SpringWebMvc的注解来定义HTTP客户端。