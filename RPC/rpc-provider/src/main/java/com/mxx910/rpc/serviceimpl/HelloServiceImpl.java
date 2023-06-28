package com.mxx910.rpc.serviceimpl;

import com.mxx910.rpc.Hello;
import com.mxx910.rpc.HelloService;
import com.mxx910.rpc.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: mxx910
 * @date: 2023/6/28
 * @description:
 */
@Slf4j
@RpcService(group = "test1", version = "version1")
public class HelloServiceImpl implements HelloService {
    static {
        System.out.println("HelloServiceImpl被创建");
    }
    @Override
    public String hello(Hello hello) {
        log.info("HelloServiceImpl收到: {}.", hello.getMessage());
        String result = "Hello description is " + hello.getDescription();
        log.info("HelloServiceImpl返回: {}.", result);
        return result;
    }
}

