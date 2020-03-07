package com.zhubome.democommonservice2.controller;


import com.zhubome.democommonmybatis.entity.User;
import com.zhubome.democommonservice2.service.IGetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IGetUserService iGetUserService;

    @GetMapping
    public List<User> getUser(){
        return iGetUserService.feignGet();
    }
}
