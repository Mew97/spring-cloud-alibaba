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

    /**
     * 引用Dubbo接口 用@Reference
     */
    @Reference
    private HelloService helloService;

    /**
     * 用feign调用服务
     * @return
     */
    @GetMapping
    public List<User> getUser() {
        return iGetUserService.feignGet();
    }

    /**
     * 用feign调用服务
     * @return
     */
    @GetMapping("/port")
    public String getPort() {
        return iGetUserService.feignGet2();
    }

    /**
     * 用Dubbo接口调用服务
     * @return
     */
    @GetMapping("/dubbo")
    public String sayHello() {
        System.out.println(helloService.sayHello("SnailClimb"));
        return helloService.sayHello("world");
    }


}
