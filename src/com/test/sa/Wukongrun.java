package com.test.sa;

import java.io.IOException;

/**
 * @author zhouj
 * @since 2020-12-09
 */
public class Wukongrun {

    public static void main(String args[]){
        new Wukong(8888);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
