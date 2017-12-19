package net.lzw.ds.tree;

public class Node {
	int data;
	Node left;
	Node right;

	//	public Node() {
	//	}
	public Node(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "[" + data + ", L=" + left + ", R=" + right + "]";
	}

}
