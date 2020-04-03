package com.zhubome.democommonservice4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoCommonService4Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoCommonService4Application.class, args);
    }

}
