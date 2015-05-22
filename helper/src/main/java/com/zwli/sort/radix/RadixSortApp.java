package com.zwli.sort.radix;

import java.util.Arrays;

public class RadixSortApp {

	public static void main(String[] args) {
		test();
		// a();
	}

	static void test() {
		int size = 10000;
		int[] iarr = createArr(size);
		int radix = 10;
		RadixSort bs = new RadixSort(iarr, radix);

		long st = System.currentTimeMillis();
		// bs.display();
		// bs.radixSort();
		bs.sort();
		// bs.display();

		long time = System.currentTimeMillis() - st;
		System.out.println("Spend Time: " + time);
	}

	static void a() {
		int size = 10;
		int[] arr = createArr(size);
		System.out.println(Arrays.toString(arr));
		for (int i = 0; i < size; i++) {
			// System.out.println("(char)" + (48 + i) + " = " + (char)(48 + i));
			System.out.print("(" + arr[i] + " / " + 1 + ") = " + (arr[i] / 1));
			System.out.println(", " + (arr[i] / 1) + " % 10 = "
					+ ((arr[i] / 1) % 10));
		}
	}

	static int[] createArr(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = (int) (Math.random() * (int) (9.999999 * size));
		}
		return arr;
	}
}
