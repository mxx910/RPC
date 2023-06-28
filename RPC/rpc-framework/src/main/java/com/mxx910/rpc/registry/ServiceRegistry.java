package com.mxx910.rpc.registry;

import com.mxx910.rpc.extension.SPI;

import java.net.InetSocketAddress;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
@SPI
public interface ServiceRegistry {
    /**
     * register service
     *
     * @param rpcServiceName    rpc service name
     * @param inetSocketAddress service address
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);

}
