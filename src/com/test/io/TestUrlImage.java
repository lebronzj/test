package com.test.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author zhouj
 * @since 16/5/7
 */
public class TestUrlImage {

    public static void main(String[] args) throws IOException {
//        URL url = new URL("http://e.hiphotos.baidu.com/image/pic/item/14ce36d3d539b600be63e95eed50352ac75cb7ae.jpg");
        URL url = new URL("http://www.baidu.com");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        Map<String,List<String>> map = urlConnection.getHeaderFields();
        for(Map.Entry<String,List<String>> entry:map.entrySet()){
            for(String string:entry.getValue()){
                System.out.println(entry.getKey()+":"+string);
            }
        }
        Socket socket = new Socket();
        System.out.println(inputStream.available());
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        for(byte b:bytes){
//            System.out.println((char)b);
        }
//        BufferedReader reader = new BufferedReader();
//        File file = new File("test.jpg");
//        OutputStream outputStream = new FileOutputStream(file);
//        outputStream.write(bytes);
    }
}
