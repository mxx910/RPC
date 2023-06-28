package com.mxx910.rpc.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: mxx910
 * @date: 2023/6/27
 * @description:
 */
@AllArgsConstructor
@Getter
public enum RpcRequestTransportEnum {

    NETTY("netty"),
    SOCKET("socket");

    private final String name;
}
