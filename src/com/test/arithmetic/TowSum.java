package com.test.arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * @author zhouj
 * @since 2017/5/24
 */
public class TowSum {

    public int[] twoSum(int[] nums, int target) {
        int count = 0;
        if(target%2==1){
            count=(target-1)/2;
        }else {
            count=target/2;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =1;i<=count;i++){
            map.put(i,target-i);
        }
        return nums;
    }

    public void findSite(int[] nums,int target,int midlle){

    }

    public static void main(String[] args) {
        System.out.println(4%2);
    }

    public int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i + 1;
                result[0] = map.get(target - numbers[i]);
                return result;
            }
            map.put(numbers[i], i + 1);
        }
        return result;
    }


}
