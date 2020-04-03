package com.zhubome.sca.consumerservice.controller;


import com.zhubome.sca.dubbointerface.service.HelloService;
import com.zhubome.sca.mybatis.entity.User;
import com.zhubome.sca.consumerservice.service.IGetUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IGetUserService iGetUserService;

    @Reference
    private HelloService helloService;

    @GetMapping
    public List<User> getUser() {
        return iGetUserService.feignGet();
    }

    @GetMapping("/port")
    public String getPort() {
        return iGetUserService.feignGet2();
    }

    @GetMapping("/dubbo")
    public String sayHello() {
        System.out.println(helloService.sayHello("SnailClimb"));
        return helloService.sayHello("world");
    }


}
