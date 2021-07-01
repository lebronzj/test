package com.test.jmh.sort;

/**
 * 归并排序：将数组递归分解成2个大小均等的数组，由合并函数负责对两个数组进行合并。
 * 合并过程：定义一个原始数组大小的临时数组，定义两个指针分别指向分解后的数组，并移动指针进行元素比较，插入到临时数组。直至递归终止，
 * 将临时数组copy回原始数组。
 *
 * @author MagicQ
 * @version 1.0
 * @date 2019/1/18
 */
public class MergeSort {
	public static void mergeSort(int[] a, int p, int r) {
		if (p >= r) {
			return;
		}
		 int q = p + ((r - p) >> 1);
//		int q = p + (r - p) / 2;
		mergeSort(a, p, q);
		mergeSort(a, q + 1, r);
		merge(a, p, q, r);

	}

	public static void merge(int[] a, int p, int q, int r) {
		int i = p;
		int j = q + 1;
		// 申请一个和a[p..r]一样大小的临时数组
		int[] tmp = new int[r - p + 1];
		int k = 0;
		while (i <= q && j <= r) {
			if (a[i] <= a[j]) {
				tmp[k++] = a[i++];
			} else {
				tmp[k++] = a[j++];

			}
		}
		// 判断哪个数组有剩余的元素
		int start = i;
		int end = q;
		if (j <= r) {
			start = j;
			end = r;
		}
		// 将剩余元素copy到临时数组
		while (start <= end) {
			tmp[k++] = a[start++];
		}
		// 将临时数组copy回原数组a[p...r]
		for (i = 0; i <= r - p; ++i) {
			a[p + i] = tmp[i];
		}

	}

	public static void printArr(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.printf("arr item is： %d\n", a[i]);
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 2, 5, 9, 12, 11, 7, 8 };
		mergeSort(a, 0, a.length - 1);
		printArr(a);
	}
}
