package com.test.net;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;

/**
 * @author zhouj
 * @since 2017/4/13
 */
public class ChannelTest {
    @SneakyThrows
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("", "rw");
            ServerSocketChannel serverSocketChannel;
            FileChannel channel = file.getChannel();
            file.getFD().sync();
            FileInputStream fileInputStream = new FileInputStream(new File(""));
            fileInputStream.getFD().sync();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(""));
            fileOutputStream.flush();

            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0L, 1000L);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
