package com.test.compile;

/**
 *
 * 通过java -cp . -Xmx3G -Xmn2G -server -XX:-DoEscapeAnalysis JVM运行代码，
 * -XX:-DoEscapeAnalysis关闭逃逸分析，通过jps查看java进程的PID，接着通过jmap -histo [pid]查看java堆上的对象分布情况，结果如下：
 * @author zhouj
 * @since 2020-06-04
 */
public class JVM {
    public static void main(String[] args) throws Exception {
        int sum = 0;
        int count = 3000000;
        //warm up
        for (int i = 0; i < count ; i++) {
            sum += fn(i);
        }

        Thread.sleep(500);

        for (int i = 0; i < count ; i++) {
            sum += fn(i);
        }

        System.out.println(sum);
        System.in.read();
    }

    private static int fn(int age) {
        User user = new User(age);
        int i = user.getAge();
        return i;
    }
}

class User {
    private final int age;

    public User(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}