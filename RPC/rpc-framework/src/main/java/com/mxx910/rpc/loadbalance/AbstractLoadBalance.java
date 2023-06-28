package com.mxx910.rpc.loadbalance;

import com.mxx910.rpc.remoting.DTO.RpcRequest;
import com.mxx910.rpc.utils.CollectionUtil;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
public abstract class AbstractLoadBalance implements LoadBalance {
    @Override
    public String selectServiceAddress(List<String> serviceAddresses, RpcRequest rpcRequest) {
        if (CollectionUtil.isEmpty(serviceAddresses)) {
            return null;
        }
        if (serviceAddresses.size() == 1) {
            return serviceAddresses.get(0);
        }
        return doSelect(serviceAddresses, rpcRequest);
    }

    protected abstract String doSelect(List<String> serviceAddresses, RpcRequest rpcRequest);

}
