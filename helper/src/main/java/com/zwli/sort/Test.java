package com.zwli.sort;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		testQuick();
		// testInsert();
	}

	static void testTime() {
		long start = System.currentTimeMillis();
		final int SIZE = 100000;
		for (int i = 0; i < SIZE; i++) {
			Integer[] randoms = createArray();
			// System.out.println(Arrays.toString(randoms));
			Sorter<Integer> sorter = new InsertSorter<Integer>();
			sorter.sort(randoms);
			// System.out.println(Arrays.toString(randoms));
		}
		long end = System.currentTimeMillis() - start;
		System.out.println("Time: " + end);
	}

	static void testInsert() {
		Integer[] randoms = createArray();
		System.out.println(Arrays.toString(randoms));
		InsertSorter<Integer> is = new InsertSorter<Integer>();
		is.sort(randoms);
		System.out.println(Arrays.toString(randoms));
	}

	static void testQuick() {
		final int SIZE = 10;
		Item[] items = new Item[SIZE];
		String name = "小明";
		int age = 20;
		int randInt;
		for (int i = 0; i < items.length; i++) {
			randInt = (int) (Math.random() * 100);
			items[i] = new Item(name + "_" + randInt, age + randInt);
		}
		System.out.println(Arrays.toString(items));

		QuickSorter<Item> qs = new QuickSorter<Item>();
		qs.sort(items);
		System.out.println(Arrays.toString(items));
	}

	static Integer[] createArray() {
		final int SIZE = 20;
		Integer[] a = new Integer[SIZE];
		for (int i = 0; i < SIZE; i++) {
			a[i] = (int) (Math.random() * 100);
		}
		return a;
	}
}
