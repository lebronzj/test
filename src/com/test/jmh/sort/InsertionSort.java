package com.test.jmh.sort;

/**
 * 插入排序（把整个数组看成2个数组，一个有序数组和一个待排序数组。
 * 循环无须数组中每一个元素依次与有序数组各元素比较，确定插入点，完成元素插入）
 *
 * @author MagicQ
 * @version 1.0
 * @date 2019/1/23
 */
public class InsertionSort {
    public static void sort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i-1;
            //元素的比较和移动
            for (; j >= 0 && a[j] > value; j--) {
                //移动
                a[j+1] = a[j];
            }
            //插入
            a[j+1] = value;
        }
    }

    public static void printArr(int[] a) {
        for (int i : a) {
            System.out.printf("item is :%d\n", i);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 2, 4, 5, 9, 8, 10, 12, 11, 6};
        sort(a, a.length);
        printArr(a);
    }
}
