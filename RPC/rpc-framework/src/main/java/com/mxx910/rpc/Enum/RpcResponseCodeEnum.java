package com.mxx910.rpc.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
@AllArgsConstructor
@Getter
@ToString
public enum RpcResponseCodeEnum {

    /**
     * 请求响应
     */
    SUCCESS(200, "The remote call is successful"),
    FAIL(500, "The remote call is fail");
    private final int code;

    private final String message;

}
