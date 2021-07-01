package com.test.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouj
 * @since 2017/5/24
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        List<Integer> arrayList1 = new ArrayList<>();
        List<Integer> arrayList2 = new ArrayList<>();
        arrayList1.add(2);
        arrayList1.add(4);
        arrayList1.add(3);
        arrayList1.add(7);
        arrayList2.add(5);
        arrayList2.add(6);
        arrayList2.add(6);
        List<Integer> ar = new AddTwoNumbers().addTwoNumbers(arrayList1,arrayList2);
        System.out.println(ar.size());

    }

    public List<Integer> addTwoNumbers(List<Integer> l1, List<Integer> l2) {
        List<Integer> returnList = new ArrayList<>();
        int prev = 0;
        int count = l2.size()>=l1.size()?l2.size():l1.size();
        for(int i =0;i<count;i++){
            if(i<l1.size()&&i<l2.size()){
                int sum = l1.get(i)+l2.get(i)+prev;
                if(sum>=10){
                    returnList.add(sum-10);
                    prev = 1;
                }else {
                    returnList.add(sum);
                    prev = 0;
                }
            }else {
                if(l1.size()<=i&&l2.size()>i){
                    returnList.add(l2.get(i)+prev);
                }
                if(l2.size()<=i&&l1.size()>i){
                    returnList.add(l1.get(i)+prev);
                }
            }

        }
        return returnList;
    }
}
