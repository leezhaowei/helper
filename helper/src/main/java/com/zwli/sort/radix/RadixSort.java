package com.zwli.sort.radix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 基数排序
 */
@SuppressWarnings("unchecked")
public class RadixSort {
    private final int[] iarr;
    private final int size;
    private List<Integer>[] radixes;
    private final int Radix;

    public RadixSort(final int[] iarr, final int Radix) {
        this.iarr = iarr;
        this.Radix = Radix;
        this.size = iarr.length;
    }

    public void display() {
        System.out.println(Arrays.toString(iarr));
    }

    /**
     * 使用链表进行排序
     */
    public void radixSort() {
        this.radixes = new LinkedList[Radix];
        for (int l = 0; l < Radix; l++) {
            this.radixes[l] = new LinkedList<Integer>();
        }

        radix();
    }

    private void radix() {
        for (int i = 0; i < Radix; i++) {
            radix(i);
            newArr();
        }
    }

    private void radix(final int i) {
        int num, index;
        // 构造数组，大小等于10（基数）
        for (int l = 0; l < Radix; l++) {
            this.radixes[l].clear();
        }

        for (int j = 0; j < size; j++) {
            num = iarr[j];
            // 寻找 个位-> 十位 -> 百位 -> 千位 -> ……
            for (int k = 1; k < i; k++) {
                num /= 10;
            }

            index = num % 10;
            radixes[index].add(iarr[j]);
        }
    }

    /**
     * 合并构成新的数组
     */
    private void newArr() {
        LinkedList<Integer> ll;
        int len = -1;
        for (int i = 0; i < Radix; i++) {
            ll = (LinkedList<Integer>) radixes[i];
            for (int j = 0; j < ll.size(); j++) {
                iarr[++len] = ll.get(j).intValue();
            }
        }
    }

    // ------------------------------------------------------------------------------------
    /**
     * 基数排序算法
     */
    public void sort() {
        int[] tmparr = new int[size];
        int[] count = new int[Radix];// 用于统计每个基数出现的次数
        int N = 1; // 用于定位个位-> 十位 -> 百位 -> 千位 -> ……
        int index;

        for (int i = 0; i < Radix; i++) {
            System.arraycopy(iarr, 0, tmparr, 0, size);
            Arrays.fill(count, 0);
            index = 0;

            for (int j = 0; j < size; j++) {
                index = (tmparr[j] / N) % Radix;
                count[index]++;
            }

            // 确定每一个数字在数组的起始位置
            for (int j = 1; j < Radix; j++) {
                count[j] = count[j] + count[j - 1];
            }

            index = 0;
            for (int j = size - 1; j >= 0; j--) {
                index = (tmparr[j] / N) % Radix;
                iarr[--count[index]] = tmparr[j];
            }

            // 1*10, 10*10, 100*10, 1000*10, ...
            N *= Radix;
        }
    }
    // ------------------------------------------------------------------------------------

}
