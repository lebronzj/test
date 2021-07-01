package com.test.time;

/**
 * @author zhouj
 * @since 16/10/13
 */
public class TestBinary {
    public static void main(String[] args) {
        String string = "九月工资还没发";
        char[] chars = string.toCharArray();
        for (int i =0;i<chars.length;i++){
            System.out.println(Integer.toBinaryString(chars[i]));
        }

        int x = 0;
        for(int i=0;i<10;i++){
            x=x+1;
        }
        System.out.println(x);
        TestBinary testBinary = new TestBinary();
        int t = testBinary.add(10);
        System.out.println(t);
        TestBinary testBinary1 = new TestBinary();
        testBinary1.getA(3);
    }


    int getA(int a){
        return a+getB(a);
    }

    int getB(int b){
        return b+getA(b);
    }

    public int add(int x){
        if(x==0){
            return x;
        }else {
            return add(x-1)+1;
        }
    }
}
