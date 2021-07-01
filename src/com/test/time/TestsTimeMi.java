package com.test.time;

import java.util.Date;

/**
 * @author zhouj
 * @since 16/5/9
 */
public class TestsTimeMi {

    public static void main(String[] args) {
        TestsTimeMi testsTimeMi = new TestsTimeMi();
        testsTimeMi.caculate(1000, 5);
    }

    public int caculate(int x, int y) {
        if (x == 10) {
            return x + y;
        } else {
            x--;
        }
        System.out.printf("调用一次caculate:{}",x);
        return caculate(x, y);
    }

}

