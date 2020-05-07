package com.infuq.thread.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ServerOutHandler extends ChannelOutboundHandlerAdapter {



    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {

        System.out.println("ServerOutHandler: " + Thread.currentThread().getName());

        ctx.writeAndFlush(msg, promise);
    }

}


