package com.test.nio.copy;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author zhouj
 * @since 2020-06-17
 */
public class TestZeroCopy {
    public static void main(String[] args) {
        File file = new File("/Users/zhouj/Downloads/crc_defEnv_771c59b85f4ea8af30ae425811d70a1c.apk");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();
            File toFile = new File("/Users/zhouj/Downloads/b.apk");
            FileOutputStream fileOutputStream = new FileOutputStream(toFile);
            FileChannel toFileChannel = fileOutputStream.getChannel();
            long time = System.currentTimeMillis();
            fileChannel.transferTo(0, file.length(), toFileChannel);
            System.out.println("time:" + (System.currentTimeMillis() - time));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
