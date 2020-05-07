package com.infuq.beat.server;

import com.infuq.beat.codec.NettyDecoder;
import com.infuq.beat.codec.NettyEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Server {


    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private EventLoopGroup businessGroup;


    public static void main(String[] args) {

        new Server().start();

    }

    private void start() {

        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        businessGroup = new NioEventLoopGroup(8);

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) {

                            ChannelPipeline channelPipeline = ch.pipeline();

                            // 自定义编解码
                            channelPipeline.addLast(new NettyDecoder());
                            channelPipeline.addLast(new NettyEncoder());

                            // 空闲检测与连接管理
                            channelPipeline.addLast(new IdleStateHandler(10, 0, 13, TimeUnit.SECONDS));
                            channelPipeline.addLast(new ConnectManageHandler());

                            channelPipeline.addLast(businessGroup, new ServerHandler()); // 简单业务处理

                            channelPipeline.addLast(new ExceptionHandler());// 简单异常处理
                        }
                    });

            // 绑定8080和8081两个端口 同步等待成功
            ChannelFuture channelFuture1 = serverBootstrap.bind("127.0.0.1", 8080).sync();
            ChannelFuture channelFuture2 = serverBootstrap.bind("127.0.0.1", 8081).sync();

            log.info("服务端等待客户端连接...");

            // 等待服务端监听端口关闭
            channelFuture1.channel().closeFuture().sync();
            channelFuture2.channel().closeFuture().sync();
        } catch (Exception ignore) {

        } finally {
            // 执行到此处说明服务端已经关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            businessGroup.shutdownGracefully();
        }


    }



}
