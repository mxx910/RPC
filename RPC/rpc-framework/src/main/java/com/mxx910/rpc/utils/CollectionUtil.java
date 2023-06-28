package com.mxx910.rpc.utils;

import java.util.Collection;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

}
