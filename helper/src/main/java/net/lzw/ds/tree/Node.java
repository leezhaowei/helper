package net.lzw.ds.tree;

public class Node {
	int value;
	char data;
	int freq;
	Node left;
	Node right;

	public Node(int data) {
		this.value = data;
	}

	@Override
	public String toString() {
		return "[" + value + ", L=" + left + ", R=" + right + "]";
	}

}
