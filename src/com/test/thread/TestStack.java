package com.test.thread;

/**
 * @author zhouj
 * @since 16/6/1
 */
public class TestStack {
    public void add(int x){
        x++;
    }

    public void addStr(String str){
        str = str+"zhou";
    }
    public void addArray(int[] ints){
        ints[0] = 3;
    }
    public static void main(String[] args) {

        //引用类型和数值类型值改变
        int i = 0;
        String str = "jie";
        String sd = new String("jie");
        String ssd = "jie";
        System.out.println(str==sd);
        System.out.println(str==ssd);
        System.out.println(str.equals(sd));
        System.out.println(str.equals(ssd));
        int[] ints = {0,1,2,3};
        TestStack testStack = new TestStack();
        testStack.add(i);
        testStack.addStr(str);
        testStack.addArray(ints);
        System.out.println(i);
        System.out.println(str);
        for(int x:ints){
            System.out.println(x);
        }
    }
}
