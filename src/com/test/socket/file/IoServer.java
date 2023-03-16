package com.test.socket.file;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhouj
 * @since 2021-07-02
 */
public class IoServer {
    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8081));
        Socket socket = serverSocket.accept();
        File file = new File("/Users/zhouj/Downloads/test.mp4");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bytes = new byte[8192];
        int n;
        System.out.println("传输文件字节:" + socket.getInputStream().available());
        int length = 0;
        while (true) {
            n = socket.getInputStream().read(bytes);
            if (n == -1) {
                break;
            }
            fileOutputStream.write(bytes);
            length += n;
        }
        System.out.println("接收文件字节:" + length);
        fileOutputStream.flush();
        fileOutputStream.close();
        socket.close();
    }
}
