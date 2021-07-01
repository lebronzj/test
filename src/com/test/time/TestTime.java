package com.test.time;

import java.util.*;

/**
 * @author zhouj
 * @since 16/12/23
 */
public class TestTime {

    public static void main(String[] args) {

        Date date = new Date(0);
        System.out.println(date);

        Person person = new Person();
        Map<String,Object> map = new HashMap<>();
        System.out.println(person.getAge());
        System.out.println(person==null?null:person.getAge());
        map.put("dssdsd",person==null?null:person.getAge());
        System.out.println("鱀:"+Integer.valueOf('鱀'));
        System.out.println("周:"+Integer.valueOf('周'));
        TestTime testTime = new TestTime();
        System.out.println(testTime.getCount(7));

    }

    public int getCount(int month){
        if(month<3) return 2;
        return getCount(month-1)+getCount(month-4);
    }

    static class Person{
        Double age;

        public Double getAge() {
            return age;
        }

        public void setAge(Double age) {
            this.age = age;
        }
    }
}
