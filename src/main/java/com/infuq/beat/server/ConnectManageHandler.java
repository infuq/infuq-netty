package com.infuq.beat.server;


import com.infuq.beat.codec.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 连接管理
 */
@Slf4j
public class ConnectManageHandler extends SimpleChannelInboundHandler<Message> {

    // 丢失的心跳包
    private int disHeartBeat;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) {

        int type = msg.getType();
        if (type == 1) {// 心跳包
            disHeartBeat = 0;
            return;
        }

        // 业务数据
        ctx.fireChannelRead(msg);
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {

            IdleStateEvent event = (IdleStateEvent) evt;

            if (event.state().equals(IdleState.READER_IDLE)) {
                if (disHeartBeat >= 3) {
                    // 连续丢失3个心跳包则断开连接
                    ctx.channel().close().sync();
                    log.info("服务端与客户端断开连接");
                } else {
                    disHeartBeat++;
                    log.info("丢失第 " + disHeartBeat + " 个心跳包");
                }
            }


        }
    }
}