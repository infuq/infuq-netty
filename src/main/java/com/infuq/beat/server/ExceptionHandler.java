package com.infuq.beat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        log.info("Error: " + cause);
        ctx.close();
    }

}
