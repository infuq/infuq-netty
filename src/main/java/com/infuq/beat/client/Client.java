package com.infuq.beat.client;

import com.infuq.beat.codec.NettyDecoder;
import com.infuq.beat.codec.NettyEncoder;
import com.infuq.beat.server.ExceptionHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Client {

    private Bootstrap bootstrap = new Bootstrap();
    private ChannelFutureListener channelFutureListener;

    private String serverIP = "127.0.0.1";
    private Integer port = 8080;


    public static void main(String[] args) {

        new Client().connect();

    }

    public void connect() {

        init();
        doConnect();

    }


    public void init() {

        EventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                        ChannelPipeline channelPipeline = ch.pipeline();

                        // 自定义编解码
                        channelPipeline.addLast(new NettyDecoder());
                        channelPipeline.addLast(new NettyEncoder());

                        // 空闲检测与连接管理
                        channelPipeline.addLast(new IdleStateHandler(0, 0, 7, TimeUnit.SECONDS));
                        channelPipeline.addLast(new ConnectManageHandler());

                        channelPipeline.addLast(new ClientHandler()); // 简单业务处理

                        channelPipeline.addLast(new ExceptionHandler());// 简单异常处理

                    }
                });

        //设置TCP协议的属性
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);

        channelFutureListener = new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    log.info("连接服务端成功");
                } else {
                    log.info("连接服务端失败");
                    // 3秒后重新连接
                    future.channel().eventLoop().schedule(new Runnable() {
                        public void run() {
                            doConnect();
                        }
                    }, 3, TimeUnit.SECONDS);
                }
            }
        };
    }

    // 连接服务端
    public void doConnect() {

        ChannelFuture future;
        try {
            future = bootstrap.connect(new InetSocketAddress(serverIP, port));
            future.addListener(channelFutureListener);

        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }


}
