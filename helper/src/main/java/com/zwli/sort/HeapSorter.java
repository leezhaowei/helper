package com.zwli.sort;

/**
 * 堆排序
 * 
 * <pre>
 *  堆是一种完全二叉树，一般使用数组来实现。
 *  堆主要有两种核心操作，
 *  1）从指定节点向上调整(shiftUp)
 *  2）从指定节点向下调整(shiftDown)
 *  建堆，以及删除堆定节点使用shiftDwon,而在插入节点时一般结合两种操作一起使用。
 *  堆排序借助最大值堆来实现，第i次从堆顶移除最大值放到数组的倒数第i个位置，然后shiftDown到倒数第i+1个位置,一共执行N此调整，即完成排序。
 *  显然，堆排序也是一种选择性的排序，每次选择第i大的元素。
 * </pre>
 * 
 * @author 李赵伟 Create: 2007-12-17
 */
public class HeapSorter<E extends Comparable<E>> extends Sorter<E> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see algorithms.Sorter#sort(E[], int, int)
	 */
	@Override
	public void sort(E[] array, int left, int len) {
		build_heap(array, left, len);
		for (int i = 0; i < len; i++) {
			// swap max value to the (len-i)-th position
			swap(array, left, left + len - 1 - i);
			shift_down(array, left, len - 1 - i, 0);// always shiftDown from 0
		}
	}

	private final void build_heap(E[] array, int left, int len) {
		// we start from (len-1)/2, because branch's node +1=leaf's node, and
		// all leaf node is already a heap
		int pos = (len - 1) / 2;
		for (int i = pos; i >= 0; i--) {
			shift_down(array, left, len, i);
		}

	}

	private final void shift_down(E[] array, int left, int len, int pos) {
		E tmp = array[left + pos];
		int index = pos * 2 + 1;// use left child
		while (index < len) { // until no child
			// right child is bigger
			if (index + 1 < len
					&& array[left + index].compareTo(array[left + index + 1]) < 0) {
				index += 1;// switch to right child
			}
			if (tmp.compareTo(array[left + index]) < 0) {
				array[left + pos] = array[left + index];
				pos = index;
				index = pos * 2 + 1;

			} else {
				break;
			}
		}
		array[left + pos] = tmp;

	}
}
