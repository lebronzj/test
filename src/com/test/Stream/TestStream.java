package com.test.Stream;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhouj
 * @since 16/10/27
 */
public class TestStream {
    public static void main(String[] args) {
        Stream.of("s","sdsd","sdsdd","sds");
        List list = new ArrayList<>();
        list.stream().map(item-> item.hashCode());
        list.forEach(item-> System.out.println(item));
        List<String> strings = new ArrayList<>();
        strings.add("dsdss");
        strings.add("sdsd");
        strings.add("smkk");
        String str = strings.stream().collect(Collectors.joining(","));
        System.out.println(str);
    }
}
