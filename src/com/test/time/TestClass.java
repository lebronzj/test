package com.test.time;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zhouj
 * @since 2017/7/10
 */
public class TestClass {


    public static void main(String[] args) {
        List<BigDecimal> list = new ArrayList<>();
        list.add(BigDecimal.ONE);
        list.add(BigDecimal.TEN);
        BigDecimal max = list.stream().max(BigDecimal::compareTo).get();
        BigDecimal min = list.stream().min(BigDecimal::compareTo).get();
        System.out.println(max);
        System.out.println(min);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        new TestClass().change(atomicInteger);
        System.out.println(atomicInteger.get());

        List<String> list1 = new ArrayList<>();
    }

    public void change(AtomicInteger atomicInteger){
        atomicInteger.incrementAndGet();
    }

}
