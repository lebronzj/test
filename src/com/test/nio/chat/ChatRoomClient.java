package com.test.nio.chat;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author zhouj
 * @since 2020-04-23
 */
public class ChatRoomClient implements Runnable {

    private SocketChannel channel;
    private Selector selector;
    private boolean active = true;
    private Charset charset = Charset.forName("UTF-8");

    private String name = "";

    public ChatRoomClient() {

        try {
            selector = Selector.open();
            channel = SocketChannel.open(new InetSocketAddress("localhost", CharRoomServer.PORT_NUM));
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("客户端监听开始：");
        while (active) {
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(selector.selectedKeys().size());
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                iterator.remove();
                if (channel == next.channel()) {
                    System.out.println("同一个对象");
                }
                System.out.println("channel:" + channel + ", sockchannel" + next.channel() + "连接事件：" + next.isConnectable() + ", " + next.isReadable() + ", " + next.isWritable());
                if (next.isConnectable()) {
                    try {
                        System.out.println("客户端连接事件");
                        SocketChannel sc = (SocketChannel) next.channel();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (next.isWritable()) {
                    System.out.println("写休眠300");
                    Thread.sleep(300);
                    System.out.println("客户端写事件");
                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                    writeBuffer.clear();
                    Scanner reader = new Scanner(System.in);
                    try {
                        String line = reader.nextLine();
                        writeBuffer.put(line.getBytes("UTF-8"));
                        writeBuffer.flip();
                        channel.write(writeBuffer);
//            channel.close();
                        channel.register(this.selector, SelectionKey.OP_READ);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (next.isReadable()) {
                    SocketChannel sc = (SocketChannel) next.channel();
                    if (channel == sc) {
                        System.out.println("同一个对象");
                    }
                    System.out.println("客户端读取事件");
                    ByteBuffer bf = ByteBuffer.allocate(2 * 1024);
                    int i = 0;
                    StringBuffer content = new StringBuffer();
                    try {
                        while ((i = sc.read(bf)) > 0) {
                            bf.flip();
                            content.append(charset.decode(bf));
                        }
                        System.out.println(content.toString());
                        next.interestOps(SelectionKey.OP_WRITE);
                    } catch (IOException e) {
                        e.printStackTrace();
                        next.cancel();
                        if (sc != null) {
                            try {
                                sc.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public static void main(String[] args) {
        ChatRoomClient chatRoomClient = new ChatRoomClient();
        new Thread(chatRoomClient).start();
        String name = chatRoomClient.getName();
        SocketChannel sc = chatRoomClient.getChannel();
        Charset charset = chatRoomClient.getCharset();
        System.out.println("请输入：");
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String data = scan.next();
            if ("".equals(data)) {
                continue;
            } else if ("".equals(name)) {
                name = data;
                data = name + CharRoomServer.protocol;
            } else {
                data = name + CharRoomServer.protocol + data;
            }
            try {
                sc.write(charset.encode(data));
            } catch (IOException e) {

            }
        }

    }
}