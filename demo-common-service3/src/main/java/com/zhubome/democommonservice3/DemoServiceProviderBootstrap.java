package com.zhubome.democommonservice3;


import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.zhubome.sca.dubbointerface.service.HelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;


/**
 * {@link HelloService} provider demo
 * 手动往Nacos上注册Dubbo服务
 */
@EnableDubbo(scanBasePackages = "com.zhubome.democommonservice3.service")
@PropertySource(value = "classpath:/provider-config.properties")
public class DemoServiceProviderBootstrap {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DemoServiceProviderBootstrap.class);
        context.refresh();
        System.out.println("DemoService provider is starting...");
        System.in.read();
    }
}