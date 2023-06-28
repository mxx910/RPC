package com.mxx910.rpc;

import com.mxx910.rpc.annotation.RpcReference;
import com.mxx910.rpc.annotation.RpcScan;
import com.mxx910.rpc.config.RpcServiceConfig;
import com.mxx910.rpc.proxy.RpcClientProxy;
import com.mxx910.rpc.remoting.transport.RpcRequestTransport;
import com.mxx910.rpc.remoting.transport.netty.client.NettyRpcClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author: mxx910
 * @date: 2023/6/28
 * @description:
 */
@RpcScan(basePackage = {"com.mxx910.rpc"})
public class NettyClientMain {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyClientMain.class);
        HelloController helloController = (HelloController) applicationContext.getBean("helloController");
        helloController.test();
    }
}

