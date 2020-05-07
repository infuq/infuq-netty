package com.infuq.beat.client;


import com.infuq.beat.codec.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * 连接管理
 *
 */
@Slf4j
public class ConnectManageHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            sendHeartbeatPacket(ctx);
        }
    }

    // 发送心跳包
    private void sendHeartbeatPacket(ChannelHandlerContext ctx) {

        Message heartBeat = new Message();
        heartBeat.setType(1);
        heartBeat.setContent("心跳包");

        log.info("发送心跳包");
        ctx.writeAndFlush(heartBeat);
    }
}