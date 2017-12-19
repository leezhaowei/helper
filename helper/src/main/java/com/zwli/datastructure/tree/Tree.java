package com.zwli.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree {

	private static class Node {
		int data;
		Node left;
		Node right;

		public Node(final int d) {
			data = d;
		}
	}

	static enum PrintOrder {
		INORDER, PREORDER, POSTORDER;
	}

	static class IntWrapper {
		int num;

		@Override
		public String toString() {
			return String.valueOf(num);
		}
	}

	Node root;
	private int countOfSingleValuedSubTree = 0;

	public Tree() {
	}

	public Tree(final Node n) {
		root = n;
	}

	private boolean countSingleRec(final Node current) {
		if (current == null) { return true; }

		boolean left = countSingleRec(current.left);
		boolean right = countSingleRec(current.right);
		if (!left || !right) { return false; }
		if (current.left != null && current.data != current.left.data) { return false; }
		if (current.right != null && current.data != current.right.data) { return false; }

		countOfSingleValuedSubTree++;
		return true;
	}

	public int countSingle() {
		countSingleRec(root);
		return countOfSingleValuedSubTree;
	}

	public void printPostorder(final Node current) {
		if (current == null) { return; }
		printPostorder(current.left);
		printPostorder(current.right);
		System.out.print(current.data + " ");
	}

	public void printInorder(final Node current) {
		if (current == null) { return; }
		printInorder(current.left);
		System.out.print(current.data + " ");
		printInorder(current.right);
	}

	public void printPreorder(final Node current) {
		if (current == null) { return; }
		System.out.print(current.data + " ");
		printInorder(current.left);
		printInorder(current.right);
	}

	public void printTree(final PrintOrder order) {
		if (PrintOrder.INORDER == order) {
			printInorder(root);
		} else if (PrintOrder.PREORDER == order) {
			printPreorder(root);
		} else if (PrintOrder.POSTORDER == order) {
			printPostorder(root);
		}
	}

	public int size() {
		return calculateSize(root);
	}

	private int calculateSize(final Node current) {
		if (current == null) {
			return 0;
		} else {
			return calculateSize(current.left) + 1 + calculateSize(current.right);
		}
	}

	public boolean identicalTrees(final Node other) {
		return validateTrees(root, other);
	}

	private boolean validateTrees(final Node current, final Node other) {
		if (current == null && other == null) { return true; }
		if (current != null && other != null) {
			boolean a = current.data == other.data;
			boolean b = validateTrees(current.left, other.left);
			boolean c = validateTrees(current.right, other.right);
			if (a && b && c) { return true; }
		}
		return false;
	}

	public int maxDepth(final Node current) {
		if (current == null) {
			return 0;
		} else {
			int leftDepth = maxDepth(current.left);
			int rightDepth = maxDepth(current.right);
			if (leftDepth > rightDepth) {
				return leftDepth + 1;
			} else {
				return rightDepth + 1;
			}
		}
	}

	public int depth(final Node root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + Math.max(depth(root.left), depth(root.right));
		}
	}

	public void mirror(final Node current) {
		if (current == null) {
			return;
		} else {
			mirror(current.left);
			mirror(current.right);

			Node tmp = null;
			tmp = current.left;
			current.left = current.right;
			current.right = tmp;
		}
	}

	public void printPaths(final Node current) {
		int path[] = new int[10];
		printPathsRecur(current, path, 0);
	}

	private void printPathsRecur(final Node current, final int[] path, int pathLen) {
		if (current == null) { return; }
		path[pathLen] = current.data;
		pathLen++;
		if (current.left == null && current.right == null) {
			printArray(path, pathLen);
		} else {
			printPathsRecur(current.left, path, pathLen);
			printPathsRecur(current.right, path, pathLen);
		}
	}

	private void printArray(final int path[], final int len) {
		for (int i = 0; i < len; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.println();
	}

	public void printLevelOrder(final Node current) {
		// TODO:
	}

	public Node createMinimalBST(final int arr[], final int start, final int end) {
		if (end < start) { return null; }
		int mid = (start + end) / 2;
		Node n = new Node(arr[mid]);
		n.left = createMinimalBST(arr, start, mid - 1);
		n.right = createMinimalBST(arr, mid + 1, end);
		return n;
	}

	public Node createMinimalBST(final int arr[]) {
		return createMinimalBST(arr, 0, arr.length - 1);
	}

	public void createLevelLinkedList(final Node current, final List<LinkedList<Node>> lists, final int level) {
		if (current == null) { return; }
		LinkedList<Node> list = null;
		if (lists.size() == level) {
			list = new LinkedList<>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(current);
		createLevelLinkedList(current.left, lists, level + 1);
		createLevelLinkedList(current.right, lists, level + 1);
	}

	public List<LinkedList<Node>> createLevelLinkedList(final Node current) {
		List<LinkedList<Node>> lists = new ArrayList<>();
		createLevelLinkedList(current, lists, 0);
		return lists;
	}

	// public void printAncestors(Node root, int key) {
	// if (root == null) { return; }
	// Stack<Node> stack = new Stack<Node>();
	// while (true) {
	// while (root != null && root.data != key) {
	// stack.push(root);
	// root = root.left;
	// }
	// if (root != null && root.data == key) {
	// break;
	// }
	// if (stack.peek().right == null) {
	// root = stack.pop();
	// while (!stack.isEmpty() && stack.peek().right == root) {
	// root = stack.pop();
	// }
	// }
	// root = stack.isEmpty() ? null : stack.peek().right;
	// }
	// while (!stack.isEmpty()) {
	// System.out.print(stack.pop().data);
	// }
	// }

	public boolean printAncestors(final Node root, final int target) {
		if (root == null) { return false; }
		if (root.data == target) { return true; }
		if (printAncestors(root.left, target) || printAncestors(root.right, target)) {
			System.out.print(root.data);
			return true;
		}
		return false;
	}

	public boolean hasPathSum(final Node node, final IntWrapper sum) {
		if (node == null) {
			return (sum.num == 0);
		} else {
			boolean ans = false;
			IntWrapper subSum = new IntWrapper();
			subSum.num = sum.num - node.data;
			if (subSum.num == 0 && node.left == null && node.right == null) { return true; }
			if (node.left != null) {
				ans = ans || hasPathSum(node.left, subSum);
			}
			if (node.right != null) {
				ans = ans || hasPathSum(node.right, subSum);
			}
			return ans;
		}
	}

	public static void main(final String[] args) {
		// testCountSingleRec();
		// testPrint();
		// testSize();
		// testIdenticalTrees();
		// testMaxDepth();
		// testMirror();
		// testPrintPaths();
		// testCreateMinimalBST();
		// testCreateLevelLinkedList();
		// testPrintAncestors();
		testHasPathSum();
	}

	static void testHasPathSum() {
		Node root = initNode(10);
		root.left = initNode(8);
		root.right = initNode(2);
		root.left.left = initNode(3);
		root.left.right = initNode(5);
		root.right.left = initNode(2);
		Tree t = new Tree();

		IntWrapper sum = new IntWrapper();
		sum.num = 21;

		if (t.hasPathSum(root, sum)) {
			System.out.println("There is a root-to-leaf path with sum " + sum);
		} else {
			System.out.println("There is no root-to-leaf path with sum " + sum);
		}
	}

	static void testPrintAncestors() {
		Node root = initNode(1);
		root.left = initNode(2);
		root.right = initNode(3);
		root.left.left = initNode(4);
		root.left.right = initNode(5);
		root.right.left = initNode(6);
		root.right.right = initNode(7);
		root.left.left.left = initNode(8);
		root.left.right.right = initNode(9);
		root.right.right.left = initNode(10);
		Tree t = new Tree();

		System.out.println("Following are all keys and their ancestors");
		for (int key = 1; key <= 10; key++) {
			System.out.print(key + " ");
			t.printAncestors(root, key);
			System.out.println();
		}
	}

	static void testCreateLevelLinkedList() {
		Node n = initTree();
		Tree t = new Tree();
		List<LinkedList<Node>> lists = t.createLevelLinkedList(n);
		for (LinkedList<Node> linkedList : lists) {
			for (Node node : linkedList) {
				System.out.print(node.data + " ");
			}
			System.out.println();
		}
	}

	static void testCreateMinimalBST() {
		int arr[] = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (i);
		}
		Tree t = new Tree();
		Node n = t.createMinimalBST(arr);
		t.printInorder(n);
		System.out.println();
		t.printPreorder(n);
		System.out.println();
		t.printPostorder(n);
	}

	static void testPrintPaths() {
		Node n = initTree();
		Tree t = new Tree();
		t.printPaths(n);
	}

	static void testMirror() {
		Node n = initTree();
		Tree t = new Tree();
		System.out.println("Inorder traversal of the constructed tree is ");
		t.printInorder(n);
		t.mirror(n);
		System.out.println("\nInorder traversal of the mirror tree is ");
		t.printInorder(n);
	}

	static void testMaxDepth() {
		Node n = initTree();
		Tree t = new Tree();
		System.out.println("Hight of tree is " + t.maxDepth(n));
	}

	private static Node initTree() {
		Node n = initNode(1);
		n.left = initNode(2);
		n.right = initNode(3);
		n.left.left = initNode(4);
		n.left.right = initNode(5);
		return n;
	}

	static void testIdenticalTrees() {
		Node a = initTree();
		Node b = initTree();

		Tree t = new Tree(a);
		System.out.println(t.identicalTrees(b));
	}

	static void testSize() {
		Node root = initTree();
		Tree t = new Tree(root);
		System.out.println("Size of the tree is " + t.size());
	}

	static void testPrint() {
		Node root = initTree();

		Tree t = new Tree(root);
		System.out.println("\nPreorder traversal of binary tree is");
		t.printTree(PrintOrder.PREORDER);
		System.out.println("\nInorder traversal of binary tree is");
		t.printTree(PrintOrder.INORDER);
		System.out.println("\nPostorder traversal of binary tree is");
		t.printTree(PrintOrder.POSTORDER);
	}

	static void testCountSingleRec() {
		Node root = initNode(5);
		root.left = initNode(4);
		root.right = initNode(5);
		root.left.left = initNode(4);
		root.left.right = initNode(4);
		root.right.right = initNode(5);
		Tree tree = new Tree(root);
		int count = tree.countSingle();
		System.out.println(count);
	}

	static Node initNode(final int data) {
		return new Node(data);
	}
}
