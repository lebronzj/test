package com.test.memory;

import lombok.Data;
import org.apache.lucene.util.RamUsageEstimator;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther zhouj
 * @since 2017/8/10
 */
public class TestMemory {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("sdsdsd");
        person.setSex("女");
        System.out.println(RamUsageEstimator.shallowSizeOf(person));
        System.out.println(RamUsageEstimator.shallowSizeOf(new Object()));
    }

    @Data
    public static class Person {
        private String name;

        private String sex;
    }
}
