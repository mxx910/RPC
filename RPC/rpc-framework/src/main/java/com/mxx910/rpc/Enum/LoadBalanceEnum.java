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
public enum LoadBalanceEnum {
    /**
     * 负载均衡
     */

    LOADBALANCE("loadBalance");

    private final String name;
}
