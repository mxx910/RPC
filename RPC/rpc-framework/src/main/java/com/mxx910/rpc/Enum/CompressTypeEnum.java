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
public enum CompressTypeEnum {
    /**
     * GZIP 压缩
     */

    GZIP((byte) 0x01, "gzip");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (CompressTypeEnum c : CompressTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

}
