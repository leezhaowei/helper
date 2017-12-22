package net.lzw.ds.hackerrank.tree;

import java.util.Random;

public class TreeGenerator {

	public static Node generateTree(String input) {
		int[] arr = parse(input.split(" "));
		Node root = new Node(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			orderInsert(root, arr[i]);
		}
		return root;
	}

	private static void orderInsert(Node root, int data) {
		Node node = new Node(data);
		if (root == null) {
			root = node;
		} else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (current.data > data) {
					current = current.left;
					if (current == null) {
						parent.left = node;
						return;
					}
				} else {
					current = current.right;
					if (current == null) {
						parent.right = node;
						return;
					}
				}
			}
		}
	}

	private static int[] parse(String[] arrStr) {
		int[] arr = new int[arrStr.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(arrStr[i]);
		}
		return arr;
	}

	public static Node generateTree() {
		return generateTree(10);
	}

	public static Node generateTree(int size) {
		int bound = 10;
		Random random = new Random();
		Node root = new Node(random.nextInt(bound));
		for (int i = 0; i < size; i++) {
			int data = random.nextInt(bound);
			orderInsert(root, data);
		}
		return root;
	}

	public static void main(String[] args) {
		int size = 10;
		generateTree(size);
	}

}
