package com.test.avg;

/**
 * @auther zhouj
 * @since 2018/11/29
 */

import java.util.*;

public class AlgorithmUtils {
    public static void taskAllocation(int task_num, int rev_num, String[][] rev_task) {

        Random rd = new Random();
        List<Integer> rdList = new ArrayList<>();
        int temp;

        //获得审核人员中的最大未完成任务数
        int max_task = Integer.parseInt(rev_task[0][1]);
        for(int i = 1; i < rev_num; i++){
            if(max_task < Integer.parseInt(rev_task[i][1]))
                max_task = Integer.parseInt(rev_task[i][1]);
        }

        //以最大待审核任务数为标杆，判断第一轮可容纳的任务数
        int ava_task = 0;
        List<Integer> lower_List = new ArrayList<>();
        for(int i=0;i<rev_num;i++){
            if((max_task-Integer.parseInt(rev_task[i][1])) > 0){
                ava_task += (max_task-Integer.parseInt(rev_task[i][1]));
                lower_List.add(i);
            }
        }

        int task_rest;
        int task_avg;
        //第一种情况：第一轮可容纳的任务数小于待分配的任务数
        if(ava_task - task_num <= 0) {
            for(int i = 0; i < rev_num; i++) {
                rev_task[i][2] = String.valueOf(max_task-Integer.parseInt(rev_task[i][1]));
            }
            task_rest = task_num-ava_task;
            task_avg = task_rest/rev_num;
            if(task_rest != 0) {
                while(task_avg > 0) {
                    for(int i = 0; i < rev_num; i++) {
                        rev_task[i][2] = String.valueOf(Integer.parseInt(rev_task[i][2])+task_avg);
                    }
                    task_rest -= rev_num*task_avg;
                    task_avg = task_rest/rev_num;
                }
                rdList.removeAll(rdList);
                while(rdList.size() < (task_rest+1)){
                    temp = rd.nextInt(rev_num);
                    if(!rdList.contains(temp)){
                        rdList.add(temp);
                    }
                }
                for(int i = 0; i < task_rest; i++) {
                    rev_task[rdList.get(i)][2] = String.valueOf(Integer.parseInt(rev_task[rdList.get(i)][2])+1);
                }
            }
        }else {//第二种情况：第一轮可容纳的任务数大于待分配的任务数，此时降低一个单位的标杆(max_task-1)，然后循环计算可容纳的任务数，直到退出循环
            while(ava_task - task_num > lower_List.size()) {
                max_task--;
                ava_task = 0;
                lower_List.removeAll(lower_List);
                for(int i=0;i<rev_num;i++){
                    rev_task[i][2] = "0";
                    if((max_task-Integer.parseInt(rev_task[i][1])) > 0){
                        rev_task[i][2] = String.valueOf(max_task-Integer.parseInt(rev_task[i][1]));
                        ava_task += Integer.parseInt(rev_task[i][2]);
                        lower_List.add(i);
                    }
                }
            }
            if(ava_task - task_num > 0) {//如果可容纳的任务数大于待分配的任务数，那么需要再再降低一个单位的标杆
                max_task--;
                ava_task = 0;
                lower_List.removeAll(lower_List);
                for(int i=0;i<rev_num;i++){
                    if((max_task-Integer.parseInt(rev_task[i][1])) >= 0){
                        rev_task[i][2] = String.valueOf(max_task-Integer.parseInt(rev_task[i][1]));
                        ava_task += Integer.parseInt(rev_task[i][2]);
                        lower_List.add(i);
                    }
                }
                task_rest = task_num - ava_task;
                rdList.removeAll(rdList);
                while(rdList.size() < (task_rest+1)){
                    temp = rd.nextInt(rev_num);
                    if((!rdList.contains(temp))&&(lower_List.contains(temp))){
                        rdList.add(temp);
                    }
                }
                for(int i = 0; i < task_rest; i++) {
                    rev_task[rdList.get(i)][2] = String.valueOf(Integer.parseInt(rev_task[rdList.get(i)][2])+1);
                }
            }else {
                task_rest = task_num-ava_task;
                if(task_rest != 0) {
                    rdList.removeAll(rdList);
                    while(rdList.size() < (task_rest+1)){
                        temp = rd.nextInt(rev_num);
                        if((!rdList.contains(temp))&&(lower_List.contains(temp))){
                            rdList.add(temp);
                        }
                    }
                    for(int i = 0; i < task_rest; i++) {
                        rev_task[rdList.get(i)][2] = String.valueOf(Integer.parseInt(rev_task[rdList.get(i)][2])+1);
                    }
                }
            }
        }

        //记录被分配的任务数
        for(int i=0;i<rev_num;i++){
            rev_task[i][1] = String.valueOf(Integer.parseInt(rev_task[i][1])+Integer.parseInt(rev_task[i][2]));
        }
    }


    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.println("请输入任务数：");
        int task_num = sc.nextInt();
        System.out.println("请输入审核人员的当前未完成任务数组，整数数字输入时用英文逗号隔开：");
        String inputString=sc.next().toString();
        String stringArray[]=inputString.split(",");

        int rev_num = stringArray.length;//审核人员总数
        String[][] rev_task =new String[rev_num][3];
        Random rd = new Random();
        List<Integer> rdList = new ArrayList<>();
        rdList.removeAll(rdList);
        int temp;
        while(rdList.size() < (rev_num+1)){
            temp = rd.nextInt(100);
            if(!rdList.contains(temp)){
                rdList.add(temp);
            }
        }

        System.out.println("算法前的任务分配：");
        for(int i=0;i<rev_num;i++){
            rev_task[i][0] = String.valueOf(rdList.get(i) + 1);
            rev_task[i][1]= stringArray[i];
            rev_task[i][2] = "0";
            System.out.print(rev_task[i][0]+","+rev_task[i][1]+" ");
        }
        System.out.println();

        AlgorithmUtils.taskAllocation(task_num, rev_num, rev_task);//调用算法工具类
        System.out.println("算法后的任务分配：");
        for(int i=0;i<rev_num;i++){
            System.out.print(rev_task[i][0]+","+rev_task[i][1]+"  ");
        }
    }

}
