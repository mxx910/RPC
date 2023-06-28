package com.mxx910.rpc.registry;

import com.mxx910.rpc.extension.SPI;
import com.mxx910.rpc.remoting.DTO.RpcRequest;

import java.net.InetSocketAddress;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
@SPI
public interface ServiceDiscovery {
    /**
     * lookup service by rpcServiceName
     *
     * @param rpcRequest rpc service pojo
     * @return service address
     */
    InetSocketAddress lookupService(RpcRequest rpcRequest);
}
