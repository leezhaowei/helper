package net.lzw.ds.hackerrank.queue;

import java.util.Scanner;
import java.util.Stack;

public class QueueUtils {

	public static void queueWithinStack(Stack<Long> front, Stack<Long> rear, int type, long item) {
		if (type == 1) {
			rear.push(item);
		} else {
			if (front.empty()) {
				while (!rear.empty()) {
					front.push(rear.pop());
				}
			}
			if (type == 2 && !front.empty()) {
				front.pop();
			} else if (type == 3 && !front.empty()) {
				System.out.println(front.peek());
			}
		}
	}

	public static void main(String[] args) {
		testQueueWithinStack();
	}

	static void testQueueWithinStack() {
		Stack<Long> front = new Stack<>();
		Stack<Long> rear = new Stack<>();
		// 1 42,2,1 14,3,1 28,3,1 60,1 78,2,2
		String input = "1 42,2,1 14,3,1 28,3,1 60,1 78,2,2";
		String[] inputArr = input.split(",");
		int n = 10;
		int type = 0;
		String t;
		for (int i = 0; i < n; i++) {
			t = inputArr[i];
			if (t.startsWith("1")) {
				type = Integer.parseInt(t.split(" ")[0]);
			} else {
				type = Integer.parseInt(t);
			}
			long item = Long.MIN_VALUE;
			if (type == 1) {
				item = Integer.parseInt(t.split(" ")[1]);
			}
			queueWithinStack(front, rear, type, item);
		}
	}

	static void testQueueWithinStackWithScanner() {
		Stack<Long> front = new Stack<>();
		Stack<Long> rear = new Stack<>();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			int type = in.nextInt();
			long item = Long.MIN_VALUE;
			if (type == 1) {
				item = in.nextLong();
			}
			queueWithinStack(front, rear, type, item);
		}
		in.close();
	}

}
