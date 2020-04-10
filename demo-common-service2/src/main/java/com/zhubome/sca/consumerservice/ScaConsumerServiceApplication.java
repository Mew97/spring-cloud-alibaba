package com.zhubome.sca.consumerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * Feign与Dubbo服务的消费者
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ScaConsumerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScaConsumerServiceApplication.class, args);
    }
}
