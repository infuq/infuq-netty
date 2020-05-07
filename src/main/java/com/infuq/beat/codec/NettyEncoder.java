package com.infuq.beat.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyEncoder extends MessageToByteEncoder<Message> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) {


        int version = 1;
        int extend = 0;
        int contentLength = msg.getContent().getBytes().length;

        out.writeInt(version);
        out.writeInt(extend);
        out.writeInt(contentLength);

        out.writeInt(msg.getType());
        out.writeBytes(msg.getContent().getBytes());

    }
}
