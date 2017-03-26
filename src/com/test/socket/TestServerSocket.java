package com.test.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

/**
 * @author zhouj
 * @since 16/5/26
 */
public class TestServerSocket {

    public void serverSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Stream<String> streams = bufferedReader.lines();
        streams.forEach(System.out::println);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("ni hao");
        serverSocket.close();
    }

    public void socket() throws IOException {
        Socket socket = new Socket("127.0.0.01",8888);
        socket.setSoTimeout(30000);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello");
        socket.close();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (serverSocket.isBound()){
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new SocketServerThread(socket));
            thread.start();
        }
    }
}
