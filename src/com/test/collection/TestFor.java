package com.test.collection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author zhouj
 * @since 16/10/31
 */
public class TestFor {
    public static void main(String[] args) throws NoSuchMethodException {
        List<String> list = new ArrayList<>();
        list.add("sds");
        list.add("fffd");
        list.add("dffd");
        list.add("cxccx");
        list.forEach(str-> System.out.println(str));
        System.out.println("==============");
//        list.forEach(s->{
//            if(s.equals("fffd")){
//                list.remove(s);
//            }
//        });
        String name = list.stream().map(str->{
            StringBuffer stringBuffer = new StringBuffer();
            if(str.contains("ff")){
                stringBuffer.append(str);
            }
            return stringBuffer;
        }).collect(Collectors.joining(","));
        System.out.println(name);
//        int a = list.size();
//        for(int i=0;i<a;i++){
//            if(i==2){
//                list.remove(2);
//            }
//        }
        list.forEach(str-> System.out.println(str));

        List<Long> integers = new ArrayList<>();
        integers.add(2l);
        integers.add(4l);
        integers.add(6l);
        integers.add(9l);
        integers.add(1l);
        integers.add(5l);
        integers.sort((x,y)->Long.compare(x,y));
        integers.forEach(x-> System.out.println(x));

        String s= "dbs";
        String t= "vt";
        String st=s+t;
        String sb="dbsvt";
        System.out.println(st==sb);
        List<String> list1 = new ArrayList<>();
        list1.add("334");
        try {
            list1.getClass().getMethod("add",Object.class).invoke(list1,23);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        for(Object str:list1){
            System.out.println(str);
        }

    }
}
