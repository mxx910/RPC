package com.mxx910.rpc;

import com.mxx910.rpc.annotation.RpcScan;
import com.mxx910.rpc.config.RpcServiceConfig;
import com.mxx910.rpc.remoting.transport.netty.server.NettyRpcServer;
import com.mxx910.rpc.serviceimpl.HelloServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: mxx910
 * @date: 2023/6/28
 * @description:
 */
@RpcScan(basePackage = {"mxx910.mxx910.rpc"})
public class NettyServerMain {
    public static void main(String[] args) {
        // Register service via annotation
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyServerMain.class);
        NettyRpcServer nettyRpcServer = (NettyRpcServer) applicationContext.getBean("nettyRpcServer");
        // Register service manually
        HelloService helloService2 = new HelloServiceImpl();
        RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                .group("test2").version("version2").service(helloService2).build();
        nettyRpcServer.registerService(rpcServiceConfig);
        nettyRpcServer.start();


    }
}
