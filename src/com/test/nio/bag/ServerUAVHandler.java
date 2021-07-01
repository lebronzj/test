package com.test.nio.bag;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhouj
 * @since 2021-02-19
 */
public class ServerUAVHandler extends ChannelInboundHandlerAdapter {
    private int counter=0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("客户端信息是："+body);
        System.out.println(body+",counter:"+ ++counter);
        ByteBuf resp= Unpooled.copiedBuffer(("A"
                +System.getProperty("line.separator")).getBytes());
        ctx.writeAndFlush(resp);
    }
}
