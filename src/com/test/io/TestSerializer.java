package com.test.io;

import java.io.*;

/**
 * Created by zhouj on 2017/2/5.
 */
public class TestSerializer {

    public static void main(String[] args) {
        File file = new File("/Users/zhouj/zhou/test.java");

        TestPerson testPerson = new TestPerson();
        testPerson.setAge(21);
        testPerson.setName("sds");
        testPerson.setSex("男");
        try {
            ObjectOutputStream  objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(testPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class TestPerson implements Serializable{
    private Integer age;

    private String name;

    private String sex;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
}
