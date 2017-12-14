package net.lzw.ds.ll;

public class IntStack {

	Node stack;

	public IntStack() {
		this.stack = null;
	}

	public void push(int val) {
		Node value = new Node(val);
		value.next = this.stack;
		this.stack = value;
	}

	public int pop() {
		if (stack == null) {
			return -1;
		} else {
			Node value = this.stack;
			this.stack = this.stack.next;
			return value.data;
		}
	}

	public int peek() {
		if (stack == null) {
			return -1;
		} else {
			return this.stack.data;
		}
	}



}
