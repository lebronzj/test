package com.test.nio;

import lombok.SneakyThrows;

import java.nio.channels.Selector;

/**
 * @author zhouj
 * @since 2021-07-02
 */
public class SelectorTest {
    @SneakyThrows
    public static void main(String[] args) {
        Selector[] selectors = new Selector[65533];
        for(int i = 0;i<selectors.length;i++){
            selectors[i] = Selector.open();
        }
        Thread.sleep(3000);
    }
}
