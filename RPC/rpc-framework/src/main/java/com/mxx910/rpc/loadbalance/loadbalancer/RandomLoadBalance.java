package com.mxx910.rpc.loadbalance.loadbalancer;

import com.mxx910.rpc.loadbalance.AbstractLoadBalance;
import com.mxx910.rpc.remoting.DTO.RpcRequest;

import java.util.List;
import java.util.Random;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
public class RandomLoadBalance extends AbstractLoadBalance {
    @Override
    protected String doSelect(List<String> serviceAddresses, RpcRequest rpcRequest) {
        Random random = new Random();
        return serviceAddresses.get(random.nextInt(serviceAddresses.size()));
    }
}
