package com.zhubome.democommonservice3.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.zhubome.sca.dubbointerface.service.HelloService;
import org.springframework.beans.factory.annotation.Value;

@Service(version = "${demo.service.version}")
public class HelloServiceImpl implements HelloService {

    @Value("${demo.service.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        RpcContext rpcContext = RpcContext.getContext();
        return String.format("Service [name :%s , port : %d] %s(\"%s\") : Hello,%s",
                serviceName,
                rpcContext.getLocalPort(),
                rpcContext.getMethodName(),
                name,
                name);
    }
}
