package com.mxx910.rpc.loadbalance;

import com.mxx910.rpc.extension.SPI;
import com.mxx910.rpc.remoting.DTO.RpcRequest;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
@SPI
public interface LoadBalance {
    /**
     * Choose one from the list of existing service addresses list
     *
     * @param serviceUrlList Service address list
     * @param rpcRequest
     * @return target service address
     */
    String selectServiceAddress(List<String> serviceUrlList, RpcRequest rpcRequest);
}
