package com.test.socket;

import java.io.*;
import java.net.Socket;
import java.util.stream.Stream;

/**
 * @author zhouj
 * @since 16/5/27
 */
public class SocketServerThread implements Runnable{
    private Socket request;


    public SocketServerThread(Socket socket){
        this.request = socket;
    }
    @Override
    public void run() {
        System.out.println("address:"+request.getInetAddress());
        System.out.println("port:"+request.getPort());
        System.out.println("remoteAddress:"+request.getRemoteSocketAddress());
        System.out.println("localAddress:"+request.getLocalAddress());
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
}
