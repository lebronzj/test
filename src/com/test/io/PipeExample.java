package com.test.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author zhouj
 * @since 16/5/9
 */
public class PipeExample {
    public static void main(String[] args) throws IOException {
        final PipedOutputStream outputStream = new PipedOutputStream();
        final PipedInputStream inputStream = new PipedInputStream(outputStream);
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    outputStream.write("zhou jie".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    int data = inputStream.read();
                    while (data!=-1){
                        System.out.println((char)data);
                        data = inputStream.read();
                        System.out.println(Integer.toBinaryString(data));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
