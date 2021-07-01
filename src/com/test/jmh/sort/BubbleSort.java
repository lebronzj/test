package com.test.jmh.sort;

import java.util.PriorityQueue;

/**
 * 冒泡排序（外层循环是冒泡次数K，内存循环是比较次数C,遵循K+C =N数组长度）
 *
 * @author MagicQ
 * @version 1.0
 * @date 2019/1/23
 */
public class BubbleSort {
    public static void sort(int[] a, int n) {
        //外层循环控制冒泡趟数K
        for (int i = 1; i < n; i++) {
            Boolean changed = false;
            //内层循环控制比较次数
            for (int j = 0; j < n - i; j++) {
                //比较
                if (a[j] > a[j + 1]) {
                    //交换前后元素
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    changed = true;
                }
            }
            //某趟冒泡未进行元素交换说明数组已有序，结束后续冒泡操作
            if (!changed) {
                break;
            }
        }
    }

    public static void printArr(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf("item is:%s\n", a[i]);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 2, 4, 5, 9, 8, 10, 12, 11, 6};
        sort(a, a.length);
        printArr(a);
        PriorityQueue pq = new PriorityQueue(10);
    }
}
