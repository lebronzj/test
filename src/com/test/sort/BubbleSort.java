package com.test.sort;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * Created by zhouj on 16/3/21.
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String args[]){
        int[] array = {1,45,13,56,32,16,23,77,99,136,47,68,72,3,17,5};
        Long timeMills = System.currentTimeMillis();
        out.println(timeMills);
        for (int i =0;i<array.length;i++){
           for (int j =0;j<array.length-i-1;j++){
               int sum;
               if(array[j]>array[j+1]){
                   sum = array[j];
                   array[j] = array[j+1];
                   array[j+1]=sum;
               }
           }
        }
        for (int i=0;i<array.length;i++)
        out.println(array[i]);
        out.println(System.currentTimeMillis()-timeMills);
        out.println("  ".length());
        int x = new BubbleSort().test();
        System.out.println("x:"+x);
        System.out.println(String.class.getClassLoader());
        System.out.println(BubbleSort.class.getClassLoader());
        out.println(timeMills);;
        out.println(timeMills.hashCode());
        out.println(Integer.toBinaryString(timeMills.hashCode()));
        out.println(Integer.toHexString(timeMills.hashCode()));
        out.println(ClassLayout.parseInstance(timeMills).hashCode());
    }


    public int test(){
        int x = 1;
        try {
            return x;
        } catch (Exception e){
            System.out.println(x);
        }finally {
            return ++x;
        }
    }
}
