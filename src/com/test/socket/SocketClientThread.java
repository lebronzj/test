package com.test.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author zhouj
 * @since 16/5/27
 */
public class SocketClientThread implements Runnable{
    private Scanner scanner;
    private Socket socket;

    public SocketClientThread(Scanner scanner,Socket socket){
        this.scanner = scanner;
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStream outputStream;
        try {
            outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeChars(scanner.next());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
            Stream<String> stream = bufferedReader.lines();
            stream.forEach(s -> {
                System.out.println(s);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
