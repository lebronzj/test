package com.test.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * 想实现磁盘顺序写的原因：
 * 最近在研究Kafka的过程中，发现kafka性能好的原因之一就是数据的最终落盘采用了磁盘的顺序读写，从各种博客和官方说法来看，磁盘的顺序读写的性能是磁盘的随机读写性能的几千倍，所以就在想，如果我要去开发一个中间件，最终存储也采用顺序读写有没有办法能用我熟悉的JAVA语言来实现这个东西呢。
 *
 * 解决问题过程：
 * 首先我去查询了JDK1.8的api文档，查看了IO包和NIO包下的一些类，发现IO下的类的介绍几乎都是随机读写的，NIO也主要是采用通道和directBytebuffer来提升性能，没能给我灵感，突然我想到了RocketMq是借鉴了Kafka的原理然后采用JAVA编写的，而且RocketMq的存储也是文件系统，然后我去查看了RocketMq的官网以及GitHub并参考了部分博客，最终发现Java实现磁盘的顺序读写主要是两个类，一个是IO包下的RandomAccessFile类和MappedByteBuffer内存映射的类来实现的，实现方案如下：
 *
 * @author zhouj
 * @since 2021-05-08
 */
public class Sequence {

    public static long fileWrite(String filePath, String content, int index) {
        File file = new File(filePath);
        RandomAccessFile randomAccessTargetFile;
        MappedByteBuffer map;
        try {
            randomAccessTargetFile = new RandomAccessFile(file, "rw");
            FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
            map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, (long) 1024 * 1024 * 1024);
            map.position(index);
            map.put(content.getBytes());
            return map.position();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return 0L;
    }

    public static String fileRead(String filePath, long index) {
        File file = new File(filePath);
        RandomAccessFile randomAccessTargetFile;
        MappedByteBuffer map;
        try {
            randomAccessTargetFile = new RandomAccessFile(file, "rw");
            FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
            map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, index);
            byte[] byteArr = new byte[10 * 1024];
            map.get(byteArr, 0, (int) index);
            return new String(byteArr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return "";
    }
}
