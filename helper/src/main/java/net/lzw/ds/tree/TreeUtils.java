package net.lzw.ds.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TreeUtils {

	public static void preOrder(Node root) {
		if (root == null) { return; }
		System.out.print(root.value + " ");
		preOrder(root.left);
		preOrder(root.right);
	}

	public static void postOrder(Node root) {
		if (root == null) { return; }
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.value + " ");
	}

	public static void inOrder(Node root) {
		if (root == null) { return; }
		inOrder(root.left);
		System.out.print(root.value + " ");
		inOrder(root.right);
	}

	public static int height(Node root) {
		if (root == null) { return 0; }
		int heightLeft = 0;
		Node current = root;
		while (current != null) {
			heightLeft++;
			current = current.left != null ? current.left : current.right;
		}

		int heightRight = 0;
		current = root;
		while (current != null) {
			heightRight++;
			current = current.right != null ? current.right : current.left;
		}
		int height = Math.max(heightLeft, heightRight) - 1;
		return height;
	}

	public static void topView(Node root) {
		Set<Integer> set = new TreeSet<>();
		Node current = root.left;
		while (current != null) {
			System.out.print(current.value + " ");
			set.add(current.value);
			current = current.left;
		}
		current = root;
		while (current != null) {
			System.out.print(current.value + " ");
			set.add(current.value);
			current = current.right;
		}
		System.out.println();
		for (Integer data : set) {
			System.out.print(data.intValue() + " ");
		}
	}

	public static void levelOrder(Node root) {
		List<LinkedList<Node>> lists = new ArrayList<>();
		levleOrder(root, lists, 0);

		for (LinkedList<Node> linkedList : lists) {
			for (Node node : linkedList) {
				System.out.print(node.value + " ");
			}
		}
	}

	public static void levleOrder(Node current, List<LinkedList<Node>> lists, int level) {
		if (current == null) { return; }
		LinkedList<Node> list = null;
		if (lists.size() == level) {
			list = new LinkedList<>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(current);
		levleOrder(current.left, lists, level + 1);
		levleOrder(current.right, lists, level + 1);
	}

	public static Node insert(Node root, int value) {
		Node node = new Node(value);
		if (root == null) { return node; }

		Node current = root;
		Node parent = null;
		while (true) {
			parent = current;
			if (current.value > value) {
				current = current.left;
				if (current == null) {
					parent.left = node;
					break;
				}
			} else {
				current = current.right;
				if (current == null) {
					parent.right = node;
					break;
				}
			}
		}
		return root;
	}

	public static void huffmanDecode(String s, Node root) {
		char[] arr = s.toCharArray();
		Node current = root;
		for (int i = 0; i < arr.length; i++) {
			int idx = arr[i] - 48;
			// System.out.print(idx);
			if (idx == 0) {
				current = current.left;
				if (current.data != '\0') {
					System.out.print(current.data);
					current = root;
					continue;
				}
			} else if (idx == 1) {
				current = current.right;
				if (current.data != '\0') {
					System.out.print(current.data);
					current = root;
					continue;
				}
			}
		}
	}

	public static Node lca(Node root, int v1, int v2) {
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		Node current = root;

		return null;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		// testOrder();
		// testHeight();
		// testTopView();
		// testLevelOrder();
		testHuffmanDecode();

		long end = System.currentTimeMillis() - start;
		System.out.println("\n\nTime: " + end);
	}

	static void testHuffmanDecode() {
		Node root = new Node(5);
		root.data = '\0';
		root.left = new Node(2);
		root.left.data = '\0';
		root.left.left = new Node(1);
		root.left.left.data = 'B';
		root.left.right = new Node(1);
		root.left.right.data = 'C';
		root.right = new Node(3);
		root.right.data = 'A';

		String s = "1001011";
		huffmanDecode(s, root);
	}

	static void testLevelOrder() {
		Node root = TreeGenerator.generateTree("1 2 5 3 4 6");
		root = TreeGenerator.generateTree(
		        "47 2 40 20 38 30 14 28 10 16 19 44 39 27 7 9 31 12 43 21 5 41 34 49 13 33 3 4 25 22 29 15 32 35 6 24 23 26 1 11 42 36 37 17 18 8 45 48 50 46");
		levelOrder(root);
	}

	static void testTopView() {
		Node root = TreeGenerator.generateTree("1 2 5 3 4 6");
		root = TreeGenerator.generateTree(
		        "47 2 40 20 38 30 14 28 10 16 19 44 39 27 7 9 31 12 43 21 5 41 34 49 13 33 3 4 25 22 29 15 32 35 6 24 23 26 1 11 42 36 37 17 18 8 45 48 50 46");
		topView(root);
	}

	static void testHeight() {
		Node root = TreeGenerator.generateTree("1 2 5 3 4 6");
		root = TreeGenerator.generateTree("7 3 5 2 1 4 6 7");
		int h = height(root);
		System.out.println(h);
	}

	static void testOrder() {
		Node root = TreeGenerator.generateTree("1 2 5 3 4 6");
		preOrder(root);
		System.out.println();
		postOrder(root);
		System.out.println();
		inOrder(root);
	}

}
