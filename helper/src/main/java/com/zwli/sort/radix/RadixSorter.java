package com.zwli.sort.radix;

import java.util.Arrays;

public class RadixSorter {
	public static boolean USE_LINK = true;

	/**
	 * @param keys
	 * @param from
	 * @param len
	 * @param radix
	 *            key's radix
	 * @param d
	 *            how many sub keys should one key divide to
	 */
	public void sort(int[] keys, int from, int len, int radix, int d) {
		if (USE_LINK) {
			link_radix_sort(keys, from, len, radix, d);
		} else {
			array_radix_sort(keys, from, len, radix, d);
		}
	}

	private final void array_radix_sort(int[] keys, int from, int len,
			int radix, int d) {
		int[] temporary = new int[len];
		int[] count = new int[radix];
		int R = 1;

		for (int i = 0; i < d; i++) {
			System.arraycopy(keys, from, temporary, 0, len);
			System.out.println("array=" + Arrays.toString(temporary));
			Arrays.fill(count, 0);

			System.out.print("* ");
			for (int k = 0; k < len; k++) {
				int subkey = (temporary[k] / R) % radix;
				count[subkey]++;
				System.out
						.print("count[" + subkey + "]=" + count[subkey] + ",");
			}

			System.out.print("\n@ ");
			for (int j = 1; j < radix; j++) {
				System.out.print("count[" + j + "]="
						+ (count[j] + count[j - 1]) + ", ");
				count[j] = count[j] + count[j - 1];
				System.out.print(count[j] + ";");
			}

			for (int m = len - 1; m >= 0; m--) {
				int subkey = (temporary[m] / R) % radix;
				--count[subkey];
				keys[from + count[subkey]] = temporary[m];
			}
			R *= radix;
			System.out.println("\n==========================");
		}

	}

	private static class LinkQueue {
		int head = -1;
		int tail = -1;
	}

	private final void link_radix_sort(int[] keys, int from, int len,
			int radix, int d) {
		int[] nexts = new int[len];
		LinkQueue[] queues = new LinkQueue[radix];
		for (int i = 0; i < radix; i++) {
			queues[i] = new LinkQueue();
		}
		for (int i = 0; i < len - 1; i++) {
			nexts[i] = i + 1;
		}
		nexts[len - 1] = -1;

		int first = 0;
		for (int i = 0; i < d; i++) {
			link_radix_sort_distribute(keys, from, len, radix, i, nexts,
					queues, first);
			first = link_radix_sort_collect(keys, from, len, radix, i, nexts,
					queues);
		}

		int[] tmps = new int[len];
		int k = 0;
		while (first != -1) {
			tmps[k++] = keys[from + first];
			first = nexts[first];
		}
		System.arraycopy(tmps, 0, keys, from, len);

	}

	private final void link_radix_sort_distribute(int[] keys, int from,
			int len, int radix, int d, int[] nexts, LinkQueue[] queues,
			int first) {

		for (int i = 0; i < radix; i++) {
			queues[i].head = queues[i].tail = -1;
		}

		while (first != -1) {
			int val = keys[from + first];
			for (int j = 0; j < d; j++) {
				val /= radix;
			}

			val = val % radix;
			if (queues[val].head == -1) {
				queues[val].head = first;
			} else {
				nexts[queues[val].tail] = first;
			}
			queues[val].tail = first;
			first = nexts[first];
		}

	}

	private int link_radix_sort_collect(int[] keys, int from, int len,
			int radix, int d, int[] nexts, LinkQueue[] queues) {
		int first = 0;
		int last = 0;
		int fromQueue = 0;
		for (; (fromQueue < radix - 1) && (queues[fromQueue].head == -1); fromQueue++) {
			;
		}
		first = queues[fromQueue].head;
		last = queues[fromQueue].tail;

		while (fromQueue < radix - 1 && queues[fromQueue].head != -1) {
			fromQueue += 1;
			for (; (fromQueue < radix - 1) && (queues[fromQueue].head == -1); fromQueue++) {
				;
			}

			nexts[last] = queues[fromQueue].head;
			last = queues[fromQueue].tail;

		}
		if (last != -1) {
			nexts[last] = -1;
		}
		return first;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int[] a = {1, 4, 8, 3, 2, 9, 5, 0, 7, 6, 9, 10, 9, 135, 14, 15, 11,
		// 222222222, 1111111111, 12, 17, 45, 16};
		int[] a = createArr(10);
		USE_LINK = false;
		RadixSorter sorter = new RadixSorter();

		long st = System.currentTimeMillis();
		sorter.sort(a, 0, a.length, 10, 10);
		long time = System.currentTimeMillis() - st;
		System.out.println("Spend Time: " + time);

		// System.out.println(Arrays.toString(a));
	}

	static int[] createArr(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = (int) (Math.random() * 999);
		}
		return arr;
	}
}
