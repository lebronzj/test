package com.test.serializable;

import java.io.Serializable;
import java.util.*;

/**
 * @author zhouj
 * @since 2017/4/19
 */
public class User implements Serializable{

    private String username;
    private int age;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {

        Map<String,String> maps = new HashMap(10000);
        for(int i = 0;i<100000;i++){
            maps.put("zhou"+i,"jie");
        }
        Long time = System.currentTimeMillis();
        for(Map.Entry entry:maps.entrySet()){
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

        }
        Long time2 = System.currentTimeMillis()-time;
        System.out.println(time2);
        time = System.currentTimeMillis();
        Iterator<Map.Entry<String,String>> iterator = maps.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String>  entry = iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
        }
        System.out.println(System.currentTimeMillis()-time);
    }
}
