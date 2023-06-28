package com.mxx910.rpc.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
@AllArgsConstructor
@Getter
public enum RpcConfigEnum {

    /**
     * RPC 配置文件路径
     *
     */
    RPC_CONFIG_PATH("rpc.properties"),
    /**
     * zookeeper地址
     */
    ZK_ADDRESS("rpc.zookeeper.address");


    /**
     *
     */
    private final String propertyValue;

}
