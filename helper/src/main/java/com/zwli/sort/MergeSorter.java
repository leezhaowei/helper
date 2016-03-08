package com.zwli.sort;

import java.lang.reflect.Array;

/**
 * 归并排序
 *
 * <pre>
 *  算法思想是每次把待排序列分成两部分，分别对这两部分递归地用归并排序，完成后把这两个子部分合并成一个
 *  序列。
 *  归并排序借助一个全局性临时数组来方便对子序列的归并，该算法核心在于归并。
 * </pre>
 */
public class MergeSorter<E extends Comparable<E>> extends Sorter<E> {

    @SuppressWarnings("unchecked")
    @Override
    public void sort(final E[] array, final int from, final int len) {
        if (len <= 1) {
            return;
        }
        E[] temporary = (E[]) Array.newInstance(array[0].getClass(), len);
        merge_sort(array, from, from + len - 1, temporary);
    }

    private void merge_sort(final E[] array, final int from, final int to, final E[] temporary) {
        if (to <= from) {
            return;
        }
        int middle = (from + to) / 2;
        merge_sort(array, from, middle, temporary);
        merge_sort(array, middle + 1, to, temporary);
        merge(array, from, to, middle, temporary);
    }

    private void merge(final E[] array, final int from, final int to, final int middle, final E[] temporary) {
        int k = 0, leftIndex = 0, rightIndex = to - from;
        System.arraycopy(array, from, temporary, 0, middle - from + 1);
        for (int i = 0; i < to - middle; i++) {
            temporary[to - from - i] = array[middle + i + 1];
        }
        while (k < to - from + 1) {
            if (temporary[leftIndex].compareTo(temporary[rightIndex]) < 0) {
                array[k + from] = temporary[leftIndex++];
            } else {
                array[k + from] = temporary[rightIndex--];
            }
            k++;
        }
    }
}
