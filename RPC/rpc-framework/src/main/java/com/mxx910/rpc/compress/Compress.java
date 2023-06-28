package com.mxx910.rpc.compress;

import com.mxx910.rpc.extension.SPI;

/**
 * @author: mxx910
 * @date: 2023/6/27
 * @description:
 */
@SPI
public interface Compress {

    byte[] compress(byte[] bytes);


    byte[] decompress(byte[] bytes);
}
