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
public enum SerializationTypeEnum {
    /**
     * 序列化类型
     */
    KYRO((byte) 0x01, "kyro"),
    PROTOSTUFF((byte) 0x02, "protostuff"),
    HESSIAN((byte) 0X03, "hessian");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (SerializationTypeEnum c : SerializationTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

}

