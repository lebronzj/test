package com.test.socket.file;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author zhouj
 * @since 2021-07-02
 */
public class IoClient {

    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8081));
        File file = new File("/Users/zhouj/Downloads/10.1周杰  黄桂清.mp4");
        FileInputStream fileInputStream = new FileInputStream(file);
        System.out.println("文件大小:" + fileInputStream.available());
        byte[] bytes = new byte[8192];
        int n = 0;
        int length = 0;
        while (true) {
            n = fileInputStream.read(bytes);
            if (n == -1) {
                break;
            }
            socket.getOutputStream().write(bytes);
            length += n;
        }
        System.out.println("传输文件字节:" + length);
        fileInputStream.close();
        socket.shutdownOutput();
        socket.close();

    }
}
