package com.test.socket.file;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhouj
 * @since 2021-07-02
 */
public class NioServer {
    @SneakyThrows
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8981));
        serverSocketChannel.configureBlocking(false);
        SocketChannel channel = serverSocketChannel.accept();
    }
}
