package com.mxx910.rpc.registry.zk;

import com.mxx910.rpc.registry.ServiceRegistry;
import com.mxx910.rpc.registry.zk.Utils.CuratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
@Slf4j
public class ZkServiceRegistryImpl implements ServiceRegistry {
    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        // 服务节点路径
        String servicePath = CuratorUtils.ZK_REGISTER_ROOT_PATH + "/" + rpcServiceName + inetSocketAddress.toString();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        // 注册服务
        CuratorUtils.createPersistentNode(zkClient, servicePath);

    }
}
