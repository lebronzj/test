package com.test.arithmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhouj on 2017/2/6.
 */
public class Test600 {

    private List<Integer> getListInteger(){
        List<Integer> list = new ArrayList<>();
        for(int i =0;i<600;i++){
            list.add(i);
        }
        return list;
    }

    private Integer getRandomOdd(int number){
        Random random = new Random();
        int returnNum;
        do {
            returnNum = 1+random.nextInt(number);
        }while (returnNum%2==0);
        return returnNum;
    }

    public static void main(String[] args) {
        int[] number = new int[601];
        Test600 test600 = new Test600();
        for(int x=0;x<100000;x++){
            List<Integer> testList = test600.getListInteger();
            for(int i=1;i<599;i++){
                Integer ramdom = test600.getRandomOdd(testList.size()-1);
                testList.remove(ramdom.intValue());
            }
            number[testList.get(1)]++;
        }

        for(int i=0;i<number.length;i++){
            System.out.println(i+":"+number[i]);
        }
    }
}
