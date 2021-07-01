package com.test.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhouj
 * @since 2020-04-23
 */
public class CharRoomServer implements Runnable {

    private ServerSocketChannel serverSocketChannel = null;
    private ServerSocketChannel serverSocketChannel2 = null;

    private Selector selector = null;

    public static final int PORT_NUM = 8080;

    private boolean active = true;

    private Charset charset = Charset.forName("UTF-8");

    private List<String> users = new ArrayList<String>();

    private ByteBuffer byteBuffer = ByteBuffer.allocate(2 * 1024);

    public static final String protocol = "#user#";

    public CharRoomServer() {
        this.init();
    }

    public void init() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT_NUM));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("开始监听。。。。");
        System.out.println("监听事件数量:" + selector.keys().size());
        while (active) {
            try {
                //非阻塞接受连接
//                int s = selector.selectNow();

                //阻塞连接
                int s = selector.select();

                System.out.println("服务端接受到连接总数" + selector.keys().size());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("服务端接受到的选择连接数" + selector.selectedKeys().size());
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey k = keys.next();
                keys.remove();
                //处理逻辑
                doHandler(selector, k);
            }
            System.out.println("主线程循环");

        }
    }

    private void doHandler(Selector selector, SelectionKey k) {
        try {
            //连接事件
        if (k.isAcceptable()) {
                ServerSocketChannel ser = (ServerSocketChannel) k.channel();
                if (ser == serverSocketChannel) {
                    System.out.println("同一个连接");
                }
                SocketChannel socketChannel = ser.accept();
                System.out.println(socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
                System.out.println("监听事件数量accept:" + selector.keys().size());
                socketChannel.write(charset.encode("please enter login name:"));

                //设置k为接受事件，准备接受其它请求？

            } else if (k.isReadable()) {
                //获取客户端连接
                SocketChannel socketChannel = (SocketChannel) k.channel();
                StringBuffer content = new StringBuffer();
                int sum = 0;
                try {
                    byteBuffer.clear();
                    while ((sum = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        content.append(charset.decode(byteBuffer));
                    }
                    System.out.println(sum);
                    //判断客户端连接关闭
                    if (sum == -1) {
                        socketChannel.close();
                        System.out.println("1--关闭连接");
                    }

                    System.out.println("服务端：监听：" + content.toString());
                } catch (Exception e) {
                    System.out.println("2--关闭连接");
                    k.cancel();
                    if (null != socketChannel) {
                        socketChannel.close();
                    }
                }
                if (content.length() > 0) {
                    //按照协议切分内容
                    String[] contents = content.toString().split(protocol);
                    //登陆用户
                    if (contents != null && contents.length == 1) {
                        String user = contents[0];
                        if (users.contains(user)) {
                            socketChannel.write(charset.encode("登陆用户已存在！"));
                        } else {
                            users.add(user);
                            //获取在线人数
                            int i = onlineCount(selector);
                            //广播登陆消息给当前房间所有人
                            brokerMessage(selector, k, "欢迎" + user + "登陆，当前第" + i + "号");
                        }
                    } else if (contents != null && contents.length > 1) {
                        String message = contents[0] + "say :" + contents[1];
                        brokerMessage(selector, k, message);
                    }
                }
                socketChannel.register(selector, SelectionKey.OP_WRITE);
                System.out.println("监听事件数量read:" + selector.keys().size());
            } else if (k.isWritable()) {
                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                writeBuffer.clear();
                Scanner reader = new Scanner(System.in);
                try {
                    SocketChannel sc = (SocketChannel) k.channel();
                    String line = reader.nextLine();
                    writeBuffer.put(line.getBytes("UTF-8"));
                    writeBuffer.flip();
                    sc.write(writeBuffer);
//            channel.close();
                    sc.register(this.selector, SelectionKey.OP_READ);
                    System.out.println("监听事件数量write:" + selector.keys().size());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 广播消息
     *
     * @param content
     */
    private void brokerMessage(Selector selector, SelectionKey k, String content) {
        for (SelectionKey key : selector.keys()) {
            if (key.channel() instanceof SocketChannel && key != k) {
                try {
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.write(charset.encode(content));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 统计在线人数
     *
     * @param selector
     * @return
     */
    private int onlineCount(Selector selector) {

        return 0;
    }

    public static void main(String[] args) {
        System.out.println("开始启动服务");
        new Thread(new CharRoomServer()).start();
        System.out.println("服务启动");
    }
}