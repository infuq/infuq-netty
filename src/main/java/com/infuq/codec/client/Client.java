package com.infuq.codec.client;

import com.example.codec.codec.OrderDecoder;
import com.example.codec.codec.OrderEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class Client {


    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {

                        ChannelPipeline channelPipeline = ch.pipeline();
                        channelPipeline.addLast(new OrderEncoder());
                        channelPipeline.addLast(new OrderDecoder());
                        channelPipeline.addLast(new ClientInHandler());

                    }
                });


        bootstrap.connect(new InetSocketAddress("127.0.0.1", 8080));

    }

}
