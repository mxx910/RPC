package com.mxx910.rpc.utils;

/**
 * @author: mxx910
 * @date: 2023/6/27
 * @description:
 */
public class RuntimeUtil {
    /**
     * 获取CPU的核心数
     *
     * @return cpu的核心数
     */
    public static int cpus() {
        return Runtime.getRuntime().availableProcessors();
    }
}
