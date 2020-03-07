package com.zhubome.democommonservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zhubome.democommonmybatis.mapper")
@ComponentScan(basePackages = {"com.zhubome.democommonservice","com.zhubome.democommonmybatis"})
public class DemoCommonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCommonServiceApplication.class, args);
    }

}
