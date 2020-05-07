package com.infuq.thread.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientInHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("接收到服务端信息" + msg);

        for (int i = 0; i < 100; i++) {
            ctx.writeAndFlush("好好学习");
        }

        Thread.sleep(1000 * 70);
        ctx.channel().read();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("异常信息" + cause.getMessage());

    }
}
