package net.lzw.ds.hackerrank.stack;

import java.util.Scanner;
import java.util.Stack;

import net.lzw.ds.hackerrank.arr.ArrayGenerator;

public class StackUtils {

	public static void maxElement() {
		class Item {
			long data;
			long max;
		}
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Stack<Item> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			int type = in.nextInt();
			if (type == 1) {
				long x = in.nextLong();
				Item it = new Item();
				it.data = x;
				if (stack.empty()) {
					it.max = it.data;
				} else {
					it.max = Math.max(it.data, stack.peek().max);
				}
				stack.push(it);
			} else if (type == 2) {
				if (!stack.empty()) {
					stack.pop();
				}
			} else if (type == 3) {
				if (!stack.empty()) {
					System.out.println(stack.peek().max);
				}
			}
		}
		in.close();
	}

	public static String isBalanced(String s) {
		char[] arr = s.toCharArray();
		if (arr.length % 2 != 0) {
			return "NO";
		}
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			char bracket = arr[i];
			if (bracket == '(' || bracket == '[' || bracket == '{') {
				stack.push(arr[i]);
			} else {
				if (stack.empty()) {
					return "NO";
				} else {
					char tmp = stack.pop();
					if ((tmp == '(' && bracket != ')') || (tmp == '[' && bracket != ']') || (tmp == '{' && bracket != '}')) {
						return "NO";
					}
				}
			}
		}
		if (!stack.empty()) {
			return "NO";
		}
		return "YES";
	}

	static Stack<Integer> setup(int[] h) {
		Stack<Integer> stack = new Stack<>();
		int len = h.length;
		int height = 0;
		for (int i = len - 1; i >= 0; i--) {
			height = h[i];
			if (!stack.empty()) {
				height += stack.peek().intValue();
			}
			stack.push(height);
		}
		return stack;
	}

	public static void equalStacks(int[] h1, int[] h2, int[] h3) {
		Stack<Integer> s1 = setup(h1);
		Stack<Integer> s2 = setup(h2);
		Stack<Integer> s3 = setup(h3);
		// System.out.println(s1);
		// System.out.println(s2);
		// System.out.println(s3);
		while (s1.peek().intValue() != s2.peek().intValue() || s2.peek().intValue() != s3.peek().intValue()) {
			if (s1.peek().intValue() > s2.peek().intValue() || s1.peek().intValue() > s3.peek().intValue()) {
				s1.pop();
			} else if (s2.peek().intValue() > s1.peek().intValue() || s2.peek().intValue() > s3.peek().intValue()) {
				s2.pop();
			} else if (s3.peek().intValue() > s1.peek().intValue() || s3.peek().intValue() > s2.peek().intValue()) {
				s3.pop();
			}
			if (s1.empty() || s2.empty() || s3.empty()) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(s1.peek().intValue());
		System.out.println(s2.peek().intValue());
		System.out.println(s3.peek().intValue());
	}

	public static void gameOfTwoStack() {
		Scanner in = new Scanner(System.in);
		int g = in.nextInt();
		for (int a0 = 0; a0 < g; a0++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int x = in.nextInt();
			int[] a = new int[n];
			for (int a_i = 0; a_i < n; a_i++) {
				a[a_i] = in.nextInt();
			}
			int[] b = new int[m];
			for (int b_i = 0; b_i < m; b_i++) {
				b[b_i] = in.nextInt();
			}
			gameOfTwoStack(n, m, x, a, b);
		}
		in.close();
	}

	public static void gameOfTwoStack(int n, int m, int x, int[] a, int[] b) {
		int sum = 0, count = 0, i = 0, j = 0;
		while (i < n && sum + a[i] <= x) { // considering only first stack and calculating count
			sum += a[i];
			i++;
		}
		count = i;
		while (j < m && i >= 0) { // now adding one element of second stack at a time and subtracting the top element of first stack and
			// calculating the count
			sum += b[j];
			j++;
			while (sum > x && i > 0) {
				i--;
				sum -= a[i];
			}
			if (sum <= x && i + j > count) {
				count = i + j;
			}
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		// testMaxElement();
		// testIsBalanced();
		// testEqualStacks();
		testGameOfTwoStack();
	}

	static void testGameOfTwoStack() {
		String s1 = "4 2 4 6 1";
		String s2 = "2 1 8 5";

		int[] a = ArrayGenerator.generateArr(s1);
		int[] b = ArrayGenerator.generateArr(s2);

		int n = 5, m = 4, x = 10;

		gameOfTwoStack(n, m, x, a, b);
	}

	static void testEqualStacks() {
		String s1 = "3 2 1 1 1";
		String s2 = "4 3 2";
		String s3 = "1 1 4 1";

		s1 = "1 1 1 1 2";
		s2 = "3 7";
		s3 = "1 3 1";

		int[] h1 = ArrayGenerator.generateArr(s1);
		int[] h2 = ArrayGenerator.generateArr(s2);
		int[] h3 = ArrayGenerator.generateArr(s3);

		equalStacks(h1, h2, h3);
	}

	static void testIsBalanced() {
		// NO,YES,YES,YES,NO,NO
		String[] arr = { "}][}}(}][))]", "[](){()}", "()", "({}([][]))[]()", "{)[](}]}]}))}(())(", "([[)" };
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i];
			String r = isBalanced(s);
			System.out.println(r);
		}
	}

	static void testMaxElement() {
		maxElement();
	}

}