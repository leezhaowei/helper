package com.zwli.sort;

/**
 * 插入排序
 *
 * <pre>
 * 该算法在数据规模小的时候十分高效，该算法每次插入第K+1到前K个有序数组中一个合适位置，
 * K从0开始到N-1,从而完成排序
 * </pre>
 */
public class InsertSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    public void sort(final E[] array, final int left, final int len) {
        E tmp = null;
        int j;
        for (int i = left + 1; i < left + len; i++) {
            tmp = array[i];
            j = i;
            for (; j > left; j--) {
                if (tmp.compareTo(array[j - 1]) < 0) {
                    array[j] = array[j - 1];
                } else {
                    break;
                }
            }
            array[j] = tmp;
        }
    }
}
