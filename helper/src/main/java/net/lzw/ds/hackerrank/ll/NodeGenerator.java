package net.lzw.ds.hackerrank.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NodeGenerator {

	public static List<Integer> getSeed(int size, int start) {
		List<Integer> buf = new ArrayList<>(size);
		int loop = size + start;
		for (int i = 0; i < size; i++) {
			// buf.add(i + 1);
			buf.add(loop - i);
		}
		return buf;
	}

	public static enum NodeType {
		NONORDER, ORDER,;
	}

	public static Node generateDoublyNode(String str) {
		Node head = generateNode(str);
		Node current = head;
		while (current != null) {
			if (current.next != null) {
				current.next.prev = current;
			}
			current = current.next;
		}
		return head;
	}

	/**
	 * @param str
	 *            The string number which split by one space.
	 */
	public static Node generateNode(String str) {
		String[] arr = str.split(" ");
		int[] arrInt = new int[arr.length];
		for (int i = 0; i < arrInt.length; i++) {
			arrInt[i] = Integer.parseInt(arr[i]);
		}
		return generateNode(arrInt);
	}

	public static Node generateNode(int[] arr) {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException();
		}
		Node head = new Node(arr[arr.length - 1]);
		for (int i = arr.length - 2; i > -1; i--) {
			head = NodeUtils.insertInFront(head, arr[i]);
		}
		return head;
	}

	public static Node generatePalindrome(int size) {
		int bound = 10;
		if (size > bound) {
			return null;
		}
		int module = size % 2;
		int half = size / 2;
		int n = 1;
		if (module != 0) {
			n += 1;
			half += 1;
		}
		List<Integer> buf = getSeed(bound, 0);
		Random random = new Random();
		int[] arr = new int[half];
		for (int i = 0; i < arr.length; i++) {
			int index = getIndex(buf, random);
			arr[i] = buf.remove(index);
		}
		Node head = new Node(arr[0]);
		Node current = head;
		for (int i = 1; i < arr.length; i++) {
			current = build(arr, current, i);
		}
		for (int i = half - n; i > -1; i--) {
			current = build(arr, current, i);
		}
		return head;
	}

	private static Node build(int[] arr, Node current, int i) {
		current.next = new Node(arr[i]);
		current = current.next;
		return current;
	}

	/**
	 * 10 size as default.
	 */
	public static Node generateNode() {
		int size = 10;
		return generateNode(size);
	}

	public static Node generateNode(int size) {
		return generateNode(size, 0);
	}

	public static Node generateNode(int size, int start) {
		return generate(size, start, NodeType.NONORDER);
	}

	public static Node generateOrderNode() {
		int size = 10;
		return generate(size, 0, NodeType.ORDER);
	}

	public static Node generate(int size, int start, NodeType nodeType) {
		List<Integer> buf = getSeed(size, start);
		return generate(size, buf, nodeType);
	}

	public static Node generate(int size, List<Integer> buf, NodeType nodeType) {
		if (buf.size() < size) {
			throw new IllegalArgumentException();
		}
		Random random = new Random();
		Node head = new Node(buf.remove(getIndex(buf, random)));
		for (int i = 1; i < size; i++) {
			int index = getIndex(buf, random);
			int data = buf.remove(index);

			if (NodeType.NONORDER == nodeType) {
				head = NodeUtils.insertInFront(head, data);
			} else
				if (NodeType.ORDER == nodeType) {
					head = NodeUtils.sortedInsert(head, data);
				}
		}
		return head;
	}

	private static int getIndex(List<Integer> buf, Random random) {
		int bound = buf.size();
		int index = random.nextInt(bound);
		return index;
	}

	public static void main(String[] args) {
		// Node head = generateNode();
		// head = generateOrderNode();
		// NodeUtils.printNode(head);

		// List<Integer> buf = getSeed(100, 0);
		// Node head1 = generate(5, buf, NodeType.NON);
		// NodeUtils.printNode(head1);
		// Node head2 = generate(5, buf, NodeType.NON);
		// NodeUtils.printNode(head2);

		// Node head = generatePalindrome(3);

		Node head = generateDoublyNode("1 2 3 4 5");

		NodeUtils.printNode(head);

	}

}
