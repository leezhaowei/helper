package net.lzw.ds.ll;

public class LinkedList {

	private Node head;

	public LinkedList(Node head) {
		super();
		this.head = head;
	}

	public Node insertInOrder(Node insert) {
		if (head == null) {
			return null;
		}

		Node previous = head;
		Node current = head.next;

		if (current == null) {
			if (previous.data < insert.data) {
				previous.next = insert;
				insert.next = null;
				return head;
			} else {
				insert.next = previous;
				return insert;
			}
		}

		while (current != null) {
			if (previous.data < insert.data && current.data >= insert.data) {
				previous.next = insert;
				insert.next = current;
				return head;
			}
			current = current.next;
			previous = previous.next;
		}

		current = insert;
		insert.next = null;

		return head;
	}

	public Node delete(int data) {
		if (head.data == data) {
			return head.next;
		}

		Node previous = null;
		Node current = head;

		while (current != null) {
			if (current.data == data) {
				previous.next = current.next;
				return head;
			} else {
				previous = current;
				current = current.next;
			}
		}

		return head;
	}

	public Node reverse() {
		Node previous = null;
		Node current = head;
		Node forward = null;

		while (current != null) {
			forward = current.next;
			current.next = previous;
			previous = current;
			current = forward;
		}

		return previous;
	}

	public Node nthToLast(int n) {
		Node lead = head;
		Node chase = head;
		int size = n - 1;

		for (int i = 0; i < size; i++) {
			if (lead == null) {
				return null;
			} else {
				lead = lead.next;
			}
		}

		while (lead.next != null) {
			lead = lead.next;
			chase = chase.next;
		}

		return chase;
	}

	public boolean hasLoop() {
		return NodeUtils.hasCycle(head);
	}

	public static void main(String[] args) {
		Node head = NodeGenerator.generateNode();
		NodeUtils.printNode(head);
		LinkedList ll = new LinkedList(head);

		int nth = 7;
		Node node = ll.nthToLast(nth);
		System.out.println(node.data);
		NodeUtils.printNode(node);

		Node reverse = ll.reverse();
		NodeUtils.printNode(reverse);
	}

}
