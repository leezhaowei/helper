package com.zwli.sort;

/**
 * 快速排序
 *
 * <pre>
 *  快速排序是目前使用可能最广泛的排序算法了。
 *  一般分如下步骤：
 *  1）选择一个枢纽元素（有很对选法，我的实现里采用去中间元素的简单方法）
 *  2）使用该枢纽元素分割数组，使得比该元素小的元素在它的左边，比它大的在右边。并把枢纽元素
 *     放在合适的位置。
 *  3）根据枢纽元素最后确定的位置，把数组分成三部分，左边的，右边的，枢纽元素自己，对左边的，
 *     右边的分别递归调用快速排序算法即可。
 *  快速排序的核心在于分割算法，也可以说是最有技巧的部分。
 * </pre>
 */
public class QuickSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    public void sort(final E[] array, final int left, final int len) {
        q_sort(array, left, left + len - 1);
    }

    private void q_sort(final E[] array, final int left, final int right) {
        if (right - left < 1) {
            return;
        }
        int pivot = selectPivot(left, right);

        pivot = partion(array, left, right, pivot);

        q_sort(array, left, pivot - 1);
        q_sort(array, pivot + 1, right);
    }

    private int partion(final E[] array, int left, int right, final int pivot) {
        E tmp = array[pivot];
        array[pivot] = array[right];// now to's position is available

        while (left != right) {
            while (left < right && array[left].compareTo(tmp) <= 0) {
                left++;
            }
            if (left < right) {
                array[right] = array[left];// now from's position is available
                right--;
            }
            while (left < right && array[right].compareTo(tmp) >= 0) {
                right--;
            }
            if (left < right) {
                array[left] = array[right];// now to's position is available
                // now
                left++;
            }
        }
        array[left] = tmp;
        return left;
    }

    // private int selectPivot(int left, int right){
    // // return (left + right) / 2;
    // return (left + right) >> 1;
    // }
}