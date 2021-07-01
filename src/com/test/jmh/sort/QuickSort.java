package com.test.jmh.sort;

/**
 * 递归实现快排
 *
 * @author MagicQ
 * @version 1.0
 * @date 2019/1/18
 */
public class QuickSort {
    public static void quickSort(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        //获取分区点
        int q = partition(a, p, r);
        quickSort(a, p, q - 1);
        quickSort(a, q + 1, r);
    }

    /**
     * 分区函数
     *
     * @param a
     * @param p
     * @param r
     * @return
     */
    public static int partition(int[] a, int p, int r) {
        //最后一个元素为基数
        int pivot = a[r];
        int i = p;
        //遍历待分区数组中的元素，与pivot比较大小，
        // 如果a[j]<pivot则将其加入到已分区数组尾部（即a[i]和a[j]互换位置）
        for (int j = p; j < r; j++) {
            if (a[j] < pivot) {
                //双指针后移
                if (i == j) {
                    ++i;
                    //加入已分区数组尾部，即数组a[i]的位置
                } else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                	/*a[i++] ^= a[j];
                	a[j] ^= a[i++];
                	a[i++] = a[j];*/
                }
            }
        }
        //将a[i]与a[r]交换
        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
        System.out.printf("i=" + i);
        return i;
    }

    public static void printArr(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf("arr item is： %d\n", a[i]);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 2, 5, 9, 12, 11, 7, 8};
        quickSort(a, 0, a.length - 1);
        printArr(a);

    }
}
