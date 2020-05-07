package com.infuq.beat.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 *
 * 简单解码器
 */
public class NettyDecoder extends LengthFieldBasedFrameDecoder {

    public NettyDecoder() {
        super(1024, 8, 4, 4, 12);
    }

    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        ByteBuf frame = (ByteBuf) super.decode(ctx, in);


        int type = frame.readInt();

        byte[] dst = new byte[frame.readableBytes()];
        frame.readBytes(dst);

        Message content = new Message();
        content.setType(type);
        content.setContent(new String(dst));

        return content;
    }


}
