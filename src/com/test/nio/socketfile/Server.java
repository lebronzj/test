package com.test.nio.socketfile;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author zhouj
 * @since 2021-07-01
 */
public class Server {
    //通道管理器
    private Selector selector;

    //获取一个ServerSocket通道，并初始化通道
    public Server init(int port) throws IOException {
        //1.获取一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        System.out.println(serverChannel.isBlocking());
        serverChannel.configureBlocking(false);////设置为非阻塞
        System.out.println(serverChannel.isBlocking());
        //2.绑定监听，配置TCP参数，例如backlog大小
        serverChannel.socket().bind(new InetSocketAddress(port));
        //3.获取通道管理器
        selector=Selector.open();
        //将通道管理器与通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，
        //只有当该事件到达时，Selector.select()会返回，否则一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);//注册channel到selector,监测接受此通道套接字的连接
        return this;
    }
    @SneakyThrows
    public void listen() throws IOException{
        System.out.println("服务器启动成功");
        boolean isRun = true;
        while(isRun){
            //当有注册的事件到达时，方法返回，否则阻塞。
            selector.select();
            //获取selector中的迭代器，选中项为注册的事件
            Iterator<SelectionKey> ite=selector.selectedKeys().iterator();
            while(ite.hasNext()){
                SelectionKey key = ite.next();
                //删除已选的key，防止重复处理
                ite.remove();
                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel)key.channel();
                    //获得客户端连接通道
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);//可以在任意位置调用这个方法，新的阻塞模式只会影响下面的i/o操作
                    //在与客户端连接成功后，为客户端通道注册SelectionKey.OP_WRITE事件。
                    channel.register(selector, SelectionKey.OP_WRITE);
                    System.out.println("客户端请求连接事件");
                }else if(key.isReadable()){

                }else if(key.isWritable()){
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    FileInputStream file = new FileInputStream("/Users/zhouj/Downloads/10.1周杰  黄桂清.mp4");
                    FileChannel fileChannel = file.getChannel();
                    //500M  堆外内存
                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(524288000);
                    System.out.println("开始写入事件");
                    while(fileChannel.position()<fileChannel.size()){
                        fileChannel.read(byteBuffer);//从文件通道读取到byteBuffer
                        byteBuffer.flip();
//                        while(byteBuffer.hasRemaining()){
                            socketChannel.write(byteBuffer);//写入通道
//                        }
                        byteBuffer.clear();//清理byteBuffer
                        System.out.println(fileChannel.position()+" "+fileChannel.size());
                    }
                    System.out.println("结束写操作");
                    socketChannel.shutdownOutput();
//                    socketChannel.close();
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Server().init(9981).listen();
    }

}