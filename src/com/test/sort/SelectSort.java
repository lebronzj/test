package com.test.sort;

import static java.lang.System.out;

/**
 * Created by zhouj on 16/3/21.
 */
public class SelectSort {
    public static void main(String args[]){

        int[] array = {1,45,13,56,32,16,23,77,99,136,47,68,72,3,17,5};
        for (int i=0;i<array.length;i++){
            for (int j=i;j<array.length;j++){
                int sum;
                if(array[i]>array[j]){
                    sum = array[i];
                    array[i]=array[j];
                    array[j]=sum;
                }
            }
        }

        for (int i=0;i<array.length;i++)
            out.println(array[i]);

    }
}
