package com.infuq.beat.client;

import com.infuq.beat.codec.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<Message> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Message data = new Message();
        data.setType(2);
        data.setContent("数据包");

        log.info("发送数据包");
        ctx.writeAndFlush(data);


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        ctx.channel().close();

        log.info("客户端准备重连");
        new Client().connect();

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) {

        log.info("接收服务端数据: " + msg.getContent());
    }
}


