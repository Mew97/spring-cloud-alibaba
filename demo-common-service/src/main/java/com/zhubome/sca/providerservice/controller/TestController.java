package com.zhubome.sca.providerservice.controller;

import com.zhubome.sca.mybatis.entity.User;
import com.zhubome.sca.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class TestController {
    private User user;

    @Autowired
    private IUserService iUserService;

    @GetMapping("get")
    public User getUser() {
        return user;
    }

    @PostMapping("set")
    public void setUser(User user) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5000);
        iUserService.save(user);
    }
}
