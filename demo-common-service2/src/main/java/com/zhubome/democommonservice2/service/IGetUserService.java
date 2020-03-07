package com.zhubome.democommonservice2.service;

import com.zhubome.democommonmybatis.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("demo-common-service")
public interface IGetUserService {
    @GetMapping("/get")
    List<User> feignGet();
}
