package com.test.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * @author zhouj
 * @since 2020-06-17
 */
public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 连接建立时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String welcome = channel.remoteAddress() + " 进入聊天室";
        channelGroup.add(channel);
        for (Channel item : channelGroup) {
            item.writeAndFlush(welcome);
        }
        test();
    }


    @SneakyThrows
    public void test(){
//        while (true){
            for (Channel item : channelGroup) {
                for(int i=1;i<100;i++){
                    String welcome = "你是"+item.id()+"\n";
                    item.writeAndFlush(welcome);
                    System.out.println("发送"+i+"次");
                }
            }
            Thread.sleep(1000);
//        }

    }
    /**
     * 连接关闭时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String quit = channel.remoteAddress() + " 退出聊天室";
        channelGroup.remove(channel);
        for (Channel item : channelGroup) {
            item.writeAndFlush(quit);
            item.flush();
        }
    }

    /**
     * 接收消息时调用（客户端 -> 服务端）
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(10, 100, 60, TimeUnit.SECONDS, new ArrayBlockingQueue(1000) {
        });
        executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Channel channel = ctx.channel();
                Channel channel1 = channelGroup.find(channel.id());
                System.out.println("remote:" + channel.remoteAddress());
                String message = "[" + channel.remoteAddress() + "]: " + (String) msg;
                System.out.println(message);
                // 等待输入消息
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                channel1.writeAndFlush(reader.readLine());
            }
        });





//        for (Channel item : channelGroup) {
//            // 消息群发给其他Client
//            if (!channel.remoteAddress().equals(item.remoteAddress())) {
//                String message = "[" + channel.remoteAddress() + "]: " + (String) msg;
//                item.writeAndFlush(message);
//            }
//        }
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
