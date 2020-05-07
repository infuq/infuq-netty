package com.infuq.codec.codec;

import com.example.codec.entity.OrderEntity;
import com.example.codec.utils.CommonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteBuffer;

public class OrderDecoder extends LengthFieldBasedFrameDecoder {


    public OrderDecoder() {
        super(8192, 4, 4, 0, 8);
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) {

        ByteBuf frame = null;
        try {

            frame = (ByteBuf) super.decode(ctx, in);
            if (null == frame) {
                return null;
            }

            int headerLength = frame.readInt();
            byte[] headerData = new byte[headerLength];
            frame.readBytes(headerData);
            System.out.println("消息中Header信息: " + new String(headerData));

            ByteBuffer byteBuffer = frame.nioBuffer();
            int length = byteBuffer.limit();
            byte[] bodyData = new byte[length];
            byteBuffer.get(bodyData);

            return CommonUtils.unserializableProtostuff(bodyData, OrderEntity.class);
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            if (null != frame) {
                frame.release();
            }
        }

        return null;
    }

}
