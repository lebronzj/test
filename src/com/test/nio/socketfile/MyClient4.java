package com.test.nio.socketfile;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zhouj
 * @since 2021-07-01
 */

public class MyClient4 {

    private final static Logger logger = Logger.getLogger(MyClient4.class.getName());

    public static void main(String[] args) throws Exception {
        new Thread(new MyRunnable()).start();
    }

    private static final class MyRunnable implements Runnable {
        public void run() {
            SocketChannel socketChannel = null;
            try {
                socketChannel = SocketChannel.open();
                SocketAddress socketAddress = new InetSocketAddress("localhost", 10000);
                socketChannel.connect(socketAddress);

                sendFile(socketChannel, new File("/Users/zhouj/Downloads/10.1周杰  黄桂清.mp4"));
//                receiveFile(socketChannel, new File("E:/test/client_receive.log"));
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            } finally {
                try {
                    socketChannel.close();
                } catch (Exception ex) {
                }
            }
        }

        /**
         * 对比 transfer 和传统io发送文件速度
         *
         * @param socketChannel
         * @param file
         * @throws IOException
         */
        private void sendFile(SocketChannel socketChannel, File file) throws IOException {
            FileInputStream fis = null;
            FileChannel channel = null;
            try {
                long time = System.currentTimeMillis();
                //transferTo 发送
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.getChannel().transferTo(0, randomAccessFile.length(), socketChannel);
                //传统io
//                fis = new FileInputStream(file);
//                channel = fis.getChannel();
//                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
//                int size = 0;
//                int length = 0;
//                while ((size = channel.read(buffer)) != -1) {
//                    buffer.rewind();
//                    buffer.limit(size);
//                    socketChannel.write(buffer);
//                    buffer.clear();
//                    length += size;
//                }
                System.out.println("发送耗时:" + (System.currentTimeMillis() - time));
                System.out.println("发送文件长度:" + randomAccessFile.length());
//                System.out.println("发送文件长度:" + length);

                socketChannel.socket().shutdownOutput();
            } finally {
                try {
                    channel.close();
                } catch (Exception ex) {
                }
                try {
                    fis.close();
                } catch (Exception ex) {
                }
            }
        }

        private void receiveFile(SocketChannel socketChannel, File file) throws IOException {
            FileOutputStream fos = null;
            FileChannel channel = null;

            try {
                fos = new FileOutputStream(file);
                channel = fos.getChannel();
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                int size = 0;
                while ((size = socketChannel.read(buffer)) != -1) {
                    buffer.flip();
                    if (size > 0) {
                        buffer.limit(size);
                        channel.write(buffer);
                        buffer.clear();
                    }
                }
            } finally {
                try {
                    channel.close();
                } catch (Exception ex) {
                }
                try {
                    fos.close();
                } catch (Exception ex) {
                }
            }
        }
    }

}
