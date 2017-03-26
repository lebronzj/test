package com.test.collection;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhouj
 * @since 16/11/11
 */
public class TestMap {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhou");
        map.put("sex","nan");
        map.put("age",21);
        map.forEach((key,value)->{
            System.out.println("key:"+key);
            System.out.println("value:"+value);
        });
        for(Object value:map.values()){
            System.out.println("value:"+value);
        }
        for(Map.Entry entry:map.entrySet()){
            System.out.println("key:"+entry.getKey());
            System.out.println("value:"+entry.getValue());
        }
        for(String string:map.keySet()){
            System.out.println("string:"+string);
        }

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(9);
        list.add(5);
        list.add(1);
        list.add(2);
        List<Integer> list1 = list.stream().sorted((x,y)->x.compareTo(y)).collect(Collectors.toList());
        list1.forEach(System.out::print);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("name","zhou");
        map1.put("name","jie");
        System.out.println("name:"+map1.get("name"));
        Integer x = null;
        boolean bool = true;
        if(bool||x.equals(3)){
            System.out.println(true);
        }
        Map<Long,Person> map3 = new HashMap<>();
        List<Person> list2 = new ArrayList<>();
        for(int i = 0;i<1000000;i++){
            Person person = new Person();
            person.setId((long) i);
            person.setName("zhou"+i);
            person.setName("nan"+i);
            list2.add(person);
            map3.put((long)i,person);
        }
        Long time1 = System.currentTimeMillis();
        System.out.println(list2.stream().filter(person -> person.getId().equals(99999L)).map(Person::getId));
        System.out.println("时间差:"+(System.currentTimeMillis()-time1));
        Long time2 = System.currentTimeMillis();
        Map<Long,Person> map2 = list2.stream().collect(Collectors.toMap(Person::getId,person -> person));
//        System.out.print(map2.get(99999L).getId());
        System.out.println("时间差:"+(System.currentTimeMillis()-time2));
        Long time3 = System.currentTimeMillis();
        for(Person person:list2){
            if(person.getId().equals(99999L)){
                System.out.println("sds");
            }
        }
        System.out.println("时间差:"+(System.currentTimeMillis()-time3));
        Long time4 = System.currentTimeMillis();
        Person person = map3.get(99999L);
        System.out.println("时间差:"+(System.currentTimeMillis()-time4));

    }

    public BigDecimal bg(BigDecimal dds){
        return null;
    }

    static class Person{
        Long id;
        String name;
        String sex;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString(){
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.toString();
        }

        public static void main(String[] args) {
            Person redBagBatch = new Person();
            Class c = redBagBatch.getClass();
            Field[] fields = c.getDeclaredFields();
            Stream<Field> stream = Stream.of(fields);
            String string = stream.map(field->{
                System.out.println("name:"+field.getName());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("stringBuilder.append(\""+field.getName()+":\"+"+"this."+field.getName()+")");
                return stringBuilder;
            }).collect(Collectors.joining(";\\n"));
            System.out.println(string);
        }
    }
}

