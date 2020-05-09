package com.infuq.codec.codec;

import com.infuq.codec.entity.OrderEntity;
import com.infuq.codec.utils.CommonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class OrderEncoder extends MessageToByteEncoder<OrderEntity> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, OrderEntity orderEntity, ByteBuf out) {


        // HDR1 | Length | HDR2-Length | HDR2 | Actual-Content
        //  4       4          4

        // 1. 用int(4字节)表示HDR1
        // 2. 用int(4字节)表示长度
        // 3. 用int(4字节)表示HDR2长度
        // 4. HDR2表示备注信息
        // 5. 订单信息


        String remark = "这个是备注信息, 可以忽略不计";
        byte[] orders = CommonUtils.serializableProtostuff(orderEntity);

        out.writeInt(128);
        out.writeInt(4 + remark.getBytes().length + orders.length);
        out.writeInt(remark.getBytes().length);

        out.writeBytes(remark.getBytes());
        out.writeBytes(orders);

    }

}
