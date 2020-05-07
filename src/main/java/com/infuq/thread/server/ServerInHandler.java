package com.infuq.thread.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ServerInHandler extends SimpleChannelInboundHandler<String> {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        ctx.fireChannelRegistered();
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.channel().writeAndFlush("Netty");

    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws  Exception {


    }

}


