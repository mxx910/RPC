package com.mxx910.rpc.remoting.transport.netty.codec;

import com.mxx910.rpc.Enum.CompressTypeEnum;
import com.mxx910.rpc.Enum.SerializationTypeEnum;
import com.mxx910.rpc.compress.Compress;
import com.mxx910.rpc.extension.ExtensionLoader;
import com.mxx910.rpc.remoting.DTO.RpcMessage;
import com.mxx910.rpc.remoting.constants.RpcConstants;
import com.mxx910.rpc.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: mxx910
 * @date: 2023/6/27
 * @description:
 */
@Slf4j
public class RpcMessageEncoder extends MessageToByteEncoder<RpcMessage> {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcMessage rpcMessage, ByteBuf out) {
        try {
            // 写入魔法值
            out.writeBytes(RpcConstants.MAGIC_NUMBER);
            // 写入版本信息
            out.writeByte(RpcConstants.VERSION);
            // 留出4byte的空间用于写入数据包长度
            out.writerIndex(out.writerIndex() + 4);
            byte messageType = rpcMessage.getMessageType();
            out.writeByte(messageType);
            out.writeByte(rpcMessage.getCodec());
            out.writeByte(CompressTypeEnum.GZIP.getCode());
            out.writeInt(ATOMIC_INTEGER.getAndIncrement());
            // 准备写入数据字节
            byte[] bodyBytes = null;
            int fullLength = RpcConstants.HEAD_LENGTH;
           // 如果是心跳数据包则不需要写入数据
            if (messageType != RpcConstants.HEARTBEAT_REQUEST_TYPE
                    && messageType != RpcConstants.HEARTBEAT_RESPONSE_TYPE) {
                // 序列化对象
                String codecName = SerializationTypeEnum.getName(rpcMessage.getCodec());
                log.info("codec name: [{}] ", codecName);
                Serializer serializer = ExtensionLoader.getExtensionLoader(Serializer.class)
                        .getExtension(codecName);
                bodyBytes = serializer.serialize(rpcMessage.getData());
                // 压缩数据包
                String compressName = CompressTypeEnum.getName(rpcMessage.getCompress());
                Compress compress = ExtensionLoader.getExtensionLoader(Compress.class)
                        .getExtension(compressName);
                bodyBytes = compress.compress(bodyBytes);
                fullLength += bodyBytes.length;
            }

            if (bodyBytes != null) {
                out.writeBytes(bodyBytes);
            }
            int writeIndex = out.writerIndex();
            // 重新定位写入指针地址
            out.writerIndex(writeIndex - fullLength + RpcConstants.MAGIC_NUMBER.length + 1);
            // 写入总长度
            out.writeInt(fullLength);
            out.writerIndex(writeIndex);
        } catch (Exception e) {
            log.error("Encode request error!", e);
        }
    }
}




