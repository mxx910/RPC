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

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyServerMain.class);
        // 从Bean中获取nettyRpc服务端
        NettyRpcServer nettyRpcServer = (NettyRpcServer) applicationContext.getBean("nettyRpcServer");
        // 开启端口
        nettyRpcServer.start();
    }
}
