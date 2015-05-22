package com.zwli.sort;

/**
 * <pre>
 * 排序算法的分类如下：
 * 1.插入排序（直接插入排序、折半插入排序、希尔排序）；
 * 2.交换排序（冒泡泡排序、快速排序）；
 * 3.选择排序（直接选择排序、堆排序）；
 * 4.归并排序；
 * 5.基数排序。
 * 
 * 关于排序方法的选择：
 * (1)若n较小(如n≤50)，可采用直接插入或直接选择排序。
 *    当记录规模较小时，直接插入排序较好；否则因为直接选择移动的记录数少于直接插人，应选直接选择排序为宜。
 * (2)若文件初始状态基本有序(指正序)，则应选用直接插人、冒泡或随机的快速排序为宜；
 * (3)若n较大，则应采用时间复杂度为O(nlgn)的排序方法：快速排序、堆排序或归并排序。
 * </pre>
 * 
 * @author 李赵伟 创建日期：2007-12-13
 * @param <E>
 */
public abstract class Sorter<E extends Comparable<E>> {

	public abstract void sort(E[] array, int left, int len);

	public final void sort(E[] array) {
		sort(array, 0, array.length);
	}

	protected final void swap(E[] array, int left, int right) {
		E tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}

	protected int selectPivot(int left, int right) {
		// return (left + right) / 2;
		return (left + right) >>> 1;
	}
}
