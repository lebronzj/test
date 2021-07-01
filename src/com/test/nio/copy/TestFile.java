package com.test.nio.copy;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhouj
 * @since 2020-06-17
 */
public class TestFile {

    public final static String FILE_PATH = "/Users/zhouj/Downloads/小C[下] .mp4";

    //public final static String FILE_PATH = "F:\\apache-tomcat-7.0.11\\webapps\\ROOT\\a.rar";

    public final static String FILE_PATH_OUT = "/Users/zhouj/Downloads/哈哈.mp4";

    public static void TransByCommonIoStream() throws Exception {

        long beginTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(new File(FILE_PATH));

        FileOutputStream fos = new FileOutputStream(new File(FILE_PATH_OUT));

        byte[] b = new byte[1024];

        int len = 0;

        while ((len = fis.read(b)) != -1) {
            fos.write(b, 0, len);
        }

        fos.flush();

        fis.close();
        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("采用传统IO FileInputStream 读取，耗时："
                + (endTime - beginTime));

    }

    public static void TransByCommonIoBufferedStream() throws Exception {

        long beginTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(new File(FILE_PATH));

        FileOutputStream fos = new FileOutputStream(new File(FILE_PATH_OUT));

        BufferedInputStream bis = new BufferedInputStream(fis);

        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] b = new byte[1024];

        int len = 0;

        while ((len = bis.read(b)) != -1) {
            bos.write(b, 0, len);
        }

        bos.flush();

        fis.close();
        fos.close();
        bis.close();
        bos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("采用传统IO BufferedInputStream 读取，耗时："
                + (endTime - beginTime));

    }

    public static void TransByCommonIoBuffered() throws Exception {

        long beginTime = System.currentTimeMillis();

        Reader br = new BufferedReader(new FileReader(new File(FILE_PATH)));
        Writer bw = new BufferedWriter(new FileWriter(new File(FILE_PATH_OUT)));

        char[] c = new char[1024];

        int len = 0;

        while ((len = br.read(c)) != -1) {
            bw.write(c, 0, len);
        }

        bw.flush();
        br.close();
        bw.close();

        long endTime = System.currentTimeMillis();

        System.out.println("采用传统IO  BufferedReader 读取，耗时："
                + (endTime - beginTime));
    }

    public static void TransByRandomAccFile() throws Exception {

        long beginTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(new File(FILE_PATH));

        RandomAccessFile raf = new RandomAccessFile(new File(FILE_PATH_OUT),
                "rw");

        byte[] b = new byte[1024];

        int len = 0;

        while ((len = fis.read(b)) != -1) {
            raf.write(b, 0, len);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("采用传统IO RandomAccessFile 读取，耗时："
                + (endTime - beginTime));

    }

    /**
     * 142      * 采用FileChannel 自带方法测试 public abstract long
     * 143      * transferFrom(ReadableByteChannel src, long position, long count) throws
     * 144      * IOException;
     * 145
     */
    public static void TransByNioFileChannel() throws Exception {

        long beginTime = System.currentTimeMillis();

        FileChannel fc = new FileInputStream(new File(FILE_PATH)).getChannel();

        FileChannel fco = new RandomAccessFile(new File(FILE_PATH_OUT), "rw").getChannel();
//        FileChannel fco = new FileOutputStream(new File(FILE_PATH_OUT)).getChannel();

        fco.transferFrom(fc, 0, fc.size());

        long endTime = System.currentTimeMillis();

        System.out.println("采用NIO FileChannel 自带方法  读取，耗时："
                + (endTime - beginTime));
    }

    public static void TransByNioFileChannelCommon() throws Exception {

        long beginTime = System.currentTimeMillis();

        FileChannel fc = new FileInputStream(new File(FILE_PATH)).getChannel();

        FileChannel fco = new RandomAccessFile(new File(FILE_PATH_OUT), "rw")
                .getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (fc.read(buf) != -1) {
            buf.flip();
            fco.write(buf);
            buf.clear();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("采用NIO FileChannel 循环 读取，耗时："
                + (endTime - beginTime));
    }

    public static void deleteFile() {
        File f = new File(FILE_PATH_OUT);
        if (f.exists()){
           f.delete();
        }
    }

    public static void main(String[] args) throws Exception {

        TransByCommonIoStream();
        deleteFile();
        TransByCommonIoBufferedStream();
        deleteFile();
        TransByCommonIoBuffered();
        deleteFile();
        TransByRandomAccFile();
        deleteFile();
        TransByNioFileChannel();
        deleteFile();
        TransByNioFileChannelCommon();
        deleteFile();
    }
}
