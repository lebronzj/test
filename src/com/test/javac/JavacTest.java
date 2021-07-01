package com.test.javac;

import com.sun.tools.javac.Main;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @author zhouj
 * @since 2020-12-08
 */
public class JavacTest {

    @SneakyThrows
    public static void main(String[] args) {
        //创建 编译器对象
        String[] strings = {"src/com/test/javac/JavacTest.java"};
        //调用编译器的 compile方法进行 编译 并接受 args 为参数，该参数就是  javac 后面携带的参数
        Process process = Runtime.getRuntime().exec("pwd");
        System.out.println(new BufferedReader(new InputStreamReader(process.getInputStream()))
                .lines().collect(Collectors.joining(System.lineSeparator())));
        Main.compile(strings);
    }
}
