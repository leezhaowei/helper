package net.lzw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static String getSmallestAndLargest(String s, int k) {
		String smallest = "";
		String largest = "";

		// Complete the function
		// 'smallest' must be the lexicographically smallest substring of length 'k'
		// 'largest' must be the lexicographically largest substring of length 'k'

		List<String> list = new ArrayList<>();
		int start = 0;
		int end = k;
		while (end <= s.length()) {
			list.add(s.substring(start, end));
			start++;
			end = start + k;
		}
		System.out.println(list);

		smallest = list.get(0);
		largest = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (smallest.compareTo(list.get(i)) > 0) {
				smallest = list.get(i);
			}
			if (largest.compareTo(list.get(i)) < 0) {
				largest = list.get(i);
			}
		}

		return smallest + "\n" + largest;
	}

	public static void main(String[] args) {
		// testFormat();
		// testString1();
		// testGetSmallestAndLargest();
		testString2();
	}

	static void testString2() {
		String a = "anagram";
		String b = "margana";

		Map<Character, Integer> mapA = new HashMap<>();
		char[] arr = a.toLowerCase().toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (mapA.containsKey(arr[i])) {
				int count = mapA.get(arr[i]) + 1;
				mapA.put(arr[i], count);
			} else {
				mapA.put(arr[i], 1);
			}
		}
		Map<Character, Integer> mapB = new HashMap<>();
		arr = b.toLowerCase().toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (mapB.containsKey(arr[i])) {
				int count = mapB.get(arr[i]) + 1;
				mapB.put(arr[i], count);
			} else {
				mapB.put(arr[i], 1);
			}
		}
		// System.out.println(mapA);
		// System.out.println(mapB);
		if (mapA.equals(mapB)) {
			System.out.println("Anagrams");
		} else {
			System.out.println("Not Anagrams");
		}
	}

	static void testGetSmallestAndLargest() {
		String s = "welcometojava";
		int k = 3;
		System.out.println(getSmallestAndLargest(s, k));
	}

	static void testString1() {
		String A = "hello";
		String B = "java";
		A = "vuut";
		B = "vuuuuu";

		int lenA = A.length();
		int lenB = B.length();
		int sum = lenA + lenB;

		char[] arrA = A.toCharArray();
		char[] arrB = B.toCharArray();
		String r = A.compareTo(B) > 0 ? "Yes" : "No";
		arrA[0] = Character.toUpperCase(arrA[0]);
		arrB[0] = Character.toUpperCase(arrB[0]);
		A = String.valueOf(arrA);
		B = String.valueOf(arrB);

		System.out.println(sum);
		System.out.println(r);
		System.out.println(A + " " + B);
	}

	static void testFormat() {
		String[] arr = { "java 100", "cpp 65", "python 50" };
		for (int i = 0; i < arr.length; i++) {
			String a = arr[i].split(" ")[0];
			int b = Integer.parseInt(arr[i].split(" ")[1]);
			System.out.print(a);
			for (int j = 0; j < 15 - a.length(); j++) {
				System.out.print(" ");
			}
			if (b >= 100) {
				System.out.println(b);
			} else {
				if (b < 10) {
					System.out.print("00");
				} else {
					System.out.print("0");
				}
				System.out.println(b);
			}
		}
	}
}
