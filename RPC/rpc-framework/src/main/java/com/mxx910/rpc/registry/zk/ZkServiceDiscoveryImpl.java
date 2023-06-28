package com.mxx910.rpc.registry.zk;

import com.mxx910.rpc.Enum.LoadBalanceEnum;
import com.mxx910.rpc.Enum.RpcErrorMessageEnum;
import com.mxx910.rpc.exception.RpcException;
import com.mxx910.rpc.extension.ExtensionLoader;
import com.mxx910.rpc.loadbalance.LoadBalance;
import com.mxx910.rpc.registry.ServiceDiscovery;
import com.mxx910.rpc.registry.zk.Utils.CuratorUtils;
import com.mxx910.rpc.remoting.DTO.RpcRequest;
import com.mxx910.rpc.utils.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
@Slf4j
public class ZkServiceDiscoveryImpl implements ServiceDiscovery {
    private final LoadBalance loadBalance;

    public ZkServiceDiscoveryImpl() {
        this.loadBalance = ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension(LoadBalanceEnum.LOADBALANCE.getName());;
    }

    @Override
    public InetSocketAddress lookupService(RpcRequest rpcRequest) {
        String rpcServiceName = rpcRequest.getRpcServiceName();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        List<String> serviceUrlList = CuratorUtils.getChildrenNodes(zkClient, rpcServiceName);
        if (CollectionUtil.isEmpty(serviceUrlList)) {
            throw new RpcException(RpcErrorMessageEnum.SERVICE_CAN_NOT_BE_FOUND, rpcServiceName);
        }
        String targetServiceUrl = loadBalance.selectServiceAddress(serviceUrlList, rpcRequest);
        log.info("Successfully found the service address:[{}]", targetServiceUrl);
        String[] socketAddressArray = targetServiceUrl.split(":");
        String host = socketAddressArray[0];
        int port = Integer.parseInt(socketAddressArray[1]);
        return new InetSocketAddress(host, port);
    }
}
