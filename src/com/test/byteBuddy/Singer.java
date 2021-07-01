package com.test.byteBuddy;

/**
 * @author zhouj
 * @since 2020-11-25
 */
public class Singer implements Singable {

    @Override
    public void sing() {
        System.out.println("I am singing...");
    }
}
