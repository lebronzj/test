package com.test.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author zhouj
 * @since 16/5/26
 */
public class TestSocket {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.01",8888);
        InetAddress inetAddress = InetAddress.getLoopbackAddress();
//        SocketAddress socketAddress = new InetSocketAddress();
        System.out.println(inetAddress.getAddress());
        System.out.println(inetAddress.getCanonicalHostName());
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());
        System.out.println(socket.getPort());
        System.out.println(socket.getKeepAlive());
        System.out.println(socket.getLocalPort());
        Scanner scanner = new Scanner(System.in);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeChars(scanner.next());
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
        Stream<String> stream = bufferedReader.lines();
        stream.forEach(s -> {
            System.out.println(s);
        });
        socket.shutdownInput();
        System.out.println(socket.isClosed());
        System.out.println(socket.isInputShutdown());
        System.out.println(socket.isOutputShutdown());
        Scanner scanner1 = new Scanner(System.in);
        OutputStream outputStream1 = socket.getOutputStream();
        DataOutputStream dataOutputStream1 = new DataOutputStream(outputStream);
        dataOutputStream.writeChars(scanner.next());
        socket.shutdownOutput();
        InputStream inputStream1 = socket.getInputStream();
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream));
        Stream<String> stream1 = bufferedReader.lines();
        stream1.forEach(s -> {
            System.out.println(s);
        });
        socket.shutdownInput();
    }
}
