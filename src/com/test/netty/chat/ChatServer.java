package com.test.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author zhouj
 * @since 2020-06-17
 */
public class ChatServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 多线程事件循环器-接收进来的连接
        EventLoopGroup workGroup = new NioEventLoopGroup(); // 多线程事件循环器-处理接收的连接

        try {
            ServerBootstrap bootstrap = new ServerBootstrap(); // 服务端启动类
            bootstrap
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class) // 管道类型
                    .option(ChannelOption.SO_BACKLOG, 1024) // 设置参数
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 初始化管道
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new StringDecoder()); // 流水线增加处理器
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new ChatServerHandler());
                        }
                    });

            // 绑定端口启动
            Channel channel = bootstrap.bind(8888).syncUninterruptibly().channel();
            // 等待结束
            channel.closeFuture().syncUninterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}

