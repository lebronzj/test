package com.test.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.stream.Stream;

/**
 * @author zhouj
 * @since 16/5/27
 */
public class SocketServerThread implements Runnable {
    Logger logger = LoggerFactory.getLogger(SocketServerThread.class);
    private Socket request;


    public SocketServerThread(Socket socket) {
        this.request = socket;
    }

    @Override
    public void run() {
        System.out.println("address:" + request.getInetAddress());
        System.out.println("port:" + request.getPort());
        System.out.println("remoteAddress:" + request.getRemoteSocketAddress());
        System.out.println("localAddress:" + request.getLocalAddress());
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Stream<String> streams = bufferedReader.lines();
        streams.forEach(System.out::println);
        try {
            request.shutdownInput();
            OutputStream outputStream = request.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("hello");
            request.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        long time = System.nanoTime();
        System.out.println("23iosdjjjjjuywu8iewriiuesldopiuwiookdl;suieriwioewekl;jsdjiokkosdiuwejijkdskiuewiweiewkewksdlkdoksek,sdjkowkooiewlksllkds");
        long time1 = System.nanoTime() - time;
        System.out.println("打印耗时:" + time1);
    }
}
