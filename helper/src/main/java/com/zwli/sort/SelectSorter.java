package com.zwli.sort;

/**
 * 选择排序
 *
 * <pre>
 *  选择排序相对于冒泡来说，它不是每次发现逆序都交换，而是在找到全局第i小的时候记下该元素位置，
 *  最后跟第i个元素交换，从而保证数组最终的有序。相对与插入排序来说，选择排序每次选出的都是全
 *  局第i小的，不会调整前i个元素了。
 * </pre>
 */
public class SelectSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    public void sort(final E[] array, final int from, final int len) {
        int smallest;
        int j;
        for (int i = 0; i < len; i++) {
            smallest = i;
            j = i + from;
            for (; j < from + len; j++) {
                if (array[j].compareTo(array[smallest]) < 0) {
                    smallest = j;
                }
            }
            swap(array, i, smallest);
        }
    }
}
