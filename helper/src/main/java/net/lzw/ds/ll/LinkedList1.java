package net.lzw.ds.ll;

public class LinkedList1 {

	Node head;

	public LinkedList1(Node head) {
		super();
		this.head = head;
	}

	public void printNthFromLast(int n) {
		Node lead = head;
		Node chase = head;

		int count = 0;
		if (head != null) {
			while (count < n) {
				if (chase == null) {
					System.out.println(n + " is greater than the no of nodes in the list.");
					return;
				}
				chase = chase.next;
				count++;
			}
			while (chase != null) {
				lead = lead.next;
				chase = chase.next;
			}
			System.out.println("Node no. " + n + " from last is " + lead.data);
			NodeUtils.printNode(lead);
		}
	}

	public static void main(String[] args) {
		Node node = NodeGenerator.generateNode();
		NodeUtils.printNode(node);
		LinkedList1 ll = new LinkedList1(node);

		int n = 7;
		ll.printNthFromLast(n);
	}

}
