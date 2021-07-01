package com.test.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouj
 * @since 2020-12-18
 */
public class RateLimiterTest {

    public static void main(String[] args) {

//每秒通过0.5，所以2秒只能有一个通过

        RateLimiter rateLimiter = RateLimiter.create(1);

        for (int i = 0; i < 10; i++) {

            Boolean aBoolean = rateLimiter.tryAcquire(10, TimeUnit.MICROSECONDS);
            if (aBoolean) {
                System.out.println("抢到");
            } else {
                System.out.println("没抢到");
            }
        }

    }
}
