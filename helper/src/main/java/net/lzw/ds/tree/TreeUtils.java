package net.lzw.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TreeUtils {

	public static void preOrder(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}

	public static void postOrder(Node root) {
		if (root == null) {
			return;
		}
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data + " ");
	}

	public static void inOrder(Node root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}

	public static int height(Node root) {
		if (root == null) {
			return 0;
		}
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
			System.out.print(current.data + " ");
			set.add(current.data);
			current = current.left;
		}
		current = root;
		while (current != null) {
			System.out.print(current.data + " ");
			set.add(current.data);
			current = current.right;
		}
		System.out.println();
		for (Integer data : set) {
			System.out.print(data.intValue() + " ");
		}
	}

	public static void levelOrder(Node root) {
		List<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
		levleOrder(root, lists, 0);

		for (LinkedList<Node> linkedList : lists) {
			for (Node node : linkedList) {
				System.out.print(node.data + " ");
			}
		}
	}

	public static void levleOrder(Node current, List<LinkedList<Node>> lists, int level) {
		if (current == null) {
			return;
		}
		LinkedList<Node> list = null;
		if (lists.size() == level) {
			list = new LinkedList<Node>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(current);
		levleOrder(current.left, lists, level + 1);
		levleOrder(current.right, lists, level + 1);
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		// testOrder();
		// testHeight();
		// testTopView();
		testLevelOrder();

		long end = System.currentTimeMillis() - start;
		System.out.println("\n\nTime: " + end);
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
