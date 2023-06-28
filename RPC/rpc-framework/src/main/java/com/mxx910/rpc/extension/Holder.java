package com.mxx910.rpc.extension;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
public class Holder<T> {

    private volatile T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
