package com.infuq.async.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerInHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        ChannelFuture channelFuture = ctx.writeAndFlush("蜀道之难难于上青天");

        channelFuture.addListener(new ChannelFutureListener() {

            public void operationComplete(ChannelFuture future) {

                System.out.println("ABC");
            }
        });

        System.out.println("123");
    }

}


