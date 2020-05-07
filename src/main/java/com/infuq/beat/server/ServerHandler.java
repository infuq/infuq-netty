package com.infuq.beat.server;

import com.infuq.beat.codec.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<Message> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) {

        log.info("服务端接收客户端数据: " + msg.getContent());
    }
}


