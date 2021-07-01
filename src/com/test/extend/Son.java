package com.test.extend;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhouj
 * @since 2020-06-04
 */
public class Son extends Parent {

    public Son(String age, String name) {
        super(age, name);
    }

    public static void main(String[] args) {
        Son son = new Son("dsd","sddssd");
        System.out.println(son.getAge());
        System.out.println(JSONObject.toJSONString(son));
    }
}
