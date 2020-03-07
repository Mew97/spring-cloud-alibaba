package com.zhubome.democommonservice.controller;


import com.zhubome.democommonmybatis.entity.User;
import com.zhubome.democommonmybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
`   1 *  前端控制器
 * </p>
 *
 * @author mew
 * @since 2020-03-05
 */
@RestController
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/get")
    public List<User> getUser(){
        return iUserService.list();
    }

    // 接受form或者url形式参数
    @PostMapping("/form")
    public User printUser2(User user){
        return user;
    }

    // 接受json格式参数
    @PostMapping("/json")
    public User printUser1(@RequestBody User user){
        return user;
    }

}

