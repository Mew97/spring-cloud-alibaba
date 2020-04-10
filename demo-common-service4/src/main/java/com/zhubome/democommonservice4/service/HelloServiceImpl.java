package com.zhubome.democommonservice4.service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.zhubome.sca.dubbointerface.service.HelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * 实现Dubbo接口 用Service注解
 */
@Service
public class HelloServiceImpl implements HelloService {


    @Override
    public String sayHello(String name) {
        RpcContext rpcContext = RpcContext.getContext();
        return "Hello " + name + " port:" + rpcContext.getLocalPort();
    }
}
