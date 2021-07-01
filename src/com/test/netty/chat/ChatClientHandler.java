package com.test.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author zhouj
 * @since 2020-06-17
 */
public class ChatClientHandler extends ChannelInboundHandlerAdapter {

    private int count = 1;

    Channel channel;

    /**
     * 接收消息时调用（服务端 -> 客户端）
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        this.channel = channel;
        String message = "[" + channel.remoteAddress() + "]: " + (String) msg;
        System.out.println(message + ";" + count + "次");
        count++;
//        // 等待输入消息
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        channel.writeAndFlush(reader.readLine());
    }


    /**
     * 捕获异常时调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

