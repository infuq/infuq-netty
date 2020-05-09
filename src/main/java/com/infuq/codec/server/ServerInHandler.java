package com.infuq.codec.server;

import com.alibaba.fastjson.JSON;
import com.infuq.codec.entity.OrderEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ServerInHandler extends SimpleChannelInboundHandler<OrderEntity> {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        ctx.fireChannelRegistered();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OrderEntity orderEntity) {

        System.out.println("服务端接收到客户端数据:" + JSON.toJSONString(orderEntity));

        orderEntity.setId("ID5555");
        orderEntity.setNo("NO5555");
        ctx.channel().writeAndFlush(orderEntity);

    }
}


