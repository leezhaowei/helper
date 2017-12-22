package net.lzw.ds.hackerrank.tree;

public class Node {
	int data;
	char value;
	int freq;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		// return "[" + data + ", L=" + left + ", R=" + right + "]";
		return data + "";
	}

}
