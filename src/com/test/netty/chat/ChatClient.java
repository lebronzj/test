package com.test.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * @author zhouj
 * @since 2020-06-17
 */
public class ChatClient {

    private static final EventLoopGroup clientEventLoopGroup = new NioEventLoopGroup();

    public static void main(String[] args) throws Exception {
        try {
            Bootstrap bootstrap = new Bootstrap(); // 客户端启动类
            bootstrap
                    .group(clientEventLoopGroup)
                    .channel(NioSocketChannel.class) // 管道类型
                    .handler(new ChannelInitializer<SocketChannel>() { // 初始化管道
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new LineBasedFrameDecoder(1024));
                            pipeline.addLast(new StringDecoder()); // 流水线加入处理器
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new ChatClientHandler());
                        }
                    });

            // 连接服务器启动
            Channel channel = bootstrap.connect("localhost", 8888).syncUninterruptibly().channel();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
