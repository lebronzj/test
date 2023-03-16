package com.test.socket;

import com.test.thread.ReentrantLockTest;
import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;
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
        Socket socket = new Socket("127.0.0.01", 8888);
        socket.setSoTimeout(30000);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello");
        socket.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                ServerSocket serverSocket = new ServerSocket(8888);
                while (serverSocket.isBound()) {
                    Socket socket = serverSocket.accept();
                    Thread thread = new Thread(new SocketServerThread(socket));
                    thread.start();
                }
            }
        }, "socket");
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(300000L);
            }
        });
        thread1.start();

        Object o = new Object();

        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (o) {
                    Thread.sleep(3000000);
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (o) {
                    o.wait();
                }
            }
        });
        thread3.start();

        ReentrantLock reentrantLock = new ReentrantLock();

        Thread thread4 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                reentrantLock.lock();
                {
                    Thread.sleep(3000000);
                }
            }
        });
        thread4.start();

        Thread thread5 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                reentrantLock.lock();
                {
                }
            }
        });
        thread5.start();

    }
}
