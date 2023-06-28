package com.mxx910.rpc.remoting.transport;

import com.mxx910.rpc.extension.SPI;
import com.mxx910.rpc.remoting.DTO.RpcRequest;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
@SPI
public interface RpcRequestTransport {
    /**
     * send rpc request to server and get result
     *
     * @param rpcRequest message body
     * @return data from server
     */
    Object sendRpcRequest(RpcRequest rpcRequest);
}
