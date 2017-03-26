package com.test.net;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * @author zhouj
 * @since 16/5/27
 */
public class TestDownLoad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            URL url = new URL(scanner.next());
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
