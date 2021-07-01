package com.test.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhouj
 * @since 2017/4/13
 */
public class TestSelecter {
    public static void main(String[] args) {

    }


    public void initServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
//        SocketChannel channel = new
//        SelectionKey selectionKey =
//        selector.select();
    }
}
