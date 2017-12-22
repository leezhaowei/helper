package net.lzw.ds.hackerrank.ll;

public class Node {

	Node prev;
	Node next;
	int data;

	public Node() {
	}

	public Node(int data) {
		this.data = data;
		prev = null;
		next = null;
	}

	@Override
	public String toString() {
		return "[next=" + next + ", data=" + data + "]";
	}

}
