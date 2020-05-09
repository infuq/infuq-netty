package com.infuq.codec.client;

import com.alibaba.fastjson.JSON;
import com.infuq.codec.entity.OrderEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientInHandler extends SimpleChannelInboundHandler<OrderEntity> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        ctx.fireChannelActive();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId("ID3333");
        orderEntity.setNo("NO3333");
        ctx.writeAndFlush(orderEntity);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OrderEntity orderEntity) {

        ctx.fireChannelRead(orderEntity);

        System.out.println("客户端接收到服务端数据: " + JSON.toJSONString(orderEntity));

    }
}
