package com.test.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhouj
 * @since 2017/5/11
 */
public class TestChannel {

    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/zhouj/Downloads/nginx命令.txt","rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            CharBuffer charBuffer = CharBuffer.allocate(48);
            int count = fileChannel.read(byteBuffer);
            while (count!=-1){
                System.out.println("Read " + count);
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.println(byteBuffer.asCharBuffer().toString());
                }
                byteBuffer.clear();
                count = fileChannel.read(byteBuffer);
            }
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
