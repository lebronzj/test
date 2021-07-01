package com.test.extend;

import lombok.Data;

/**
 * @author zhouj
 * @since 2020-06-04
 */
@Data
public class Parent {

    private String name;

    private String age;

    public Parent(String age,String name) {
        this.age = "20";
        this.name = "zhou";
        System.out.println("父亲构造方法");
    }
}
