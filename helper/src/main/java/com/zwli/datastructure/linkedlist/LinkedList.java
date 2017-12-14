package com.zwli.datastructure.linkedlist;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class LinkedList {
	Node head;

	class Node {
		int data;
		Node next;

		Node(final int d) {
			data = d;
			next = null;
		}

		@Override
		public String toString() {
			return data + " next:" + next;
		}
	}

	public void push(final int d) {
		Node n = new Node(d);
		n.next = head;
		head = n;
	}

	public void insertAfter(final Node prevNode, final int d) {
		if (null == prevNode) {
			System.out.println("The given previous node cannot null!");
			return;
		}
		Node n = new Node(d);
		n.next = prevNode.next;
		prevNode.next = n;
	}

	public void append(final int d) {
		Node n = new Node(d);
		if (null == head) {
			head = n;
			return;
		}
		n.next = null;
		Node last = traverse(head);
		last.next = n;
	}

	public void printList(Node n) {
		while (null != n) {
			System.out.print(n.data + "->");
			n = n.next;
		}
		System.out.println();
	}

	public void printList() {
		Node n = head;
		while (null != n) {
			System.out.print(n.data + "->");
			n = n.next;
		}
		System.out.println();
	}

	private Node traverse(Node last) {
		while (null != last.next) {
			last = last.next;
		}
		return last;
	}

	public void deleteNode(final int key) {
		Node temp = head;
		if (temp != null && temp.data == key) {
			head = temp.next;
			return;
		}
		while (temp.next != null && temp.next.data != key) {
			temp = temp.next;
		}
		if (temp == null || temp.next == null) {
			return;
		}
		temp.next = temp.next.next;
	}

	public void deleteNodeByPosition(final int position) {
		if (head == null) {
			return;
		}
		Node temp = head;
		if (position == 0) {
			head = temp.next;
			return;
		}
		for (int i = 0; temp != null && i < position - 1; i++) {
			temp = temp.next;
		}
		if (temp == null || temp.next == null) {
			return;
		}
		temp.next = temp.next.next;
	}

	public void deleteNodeByNode(final Node target) {
		Node temp = target.next;
		target.data = temp.data;
		target.next = temp.next;
		temp = null;
	}

	public void swapNodes(final int x, final int y) {
		if (x == y) {
			return;
		}

		Node prevX = null, currX = head;
		while (currX != null && currX.data != x) {
			prevX = currX;
			currX = currX.next;
		}

		Node prevY = null, currY = head;
		while (currY != null && currY.data != y) {
			prevY = currY;
			currY = currY.next;
		}

		if (currX == null || currY == null) {
			return;
		}

		if (prevX != null) {
			prevX.next = currY;
		} else {
			head = currY;
		}

		if (prevY != null) {
			prevY.next = currX;
		} else {
			head = currX;
		}

		Node temp = currX.next;
		currX.next = currY.next;
		currY.next = temp;
	}

	public int getNth(final int index) {
		Node current = head;
		int count = 0;

		while (current != null) {
			if (count == index) {
				return current.data;
			}
			count++;
			current = current.next;
		}
		System.out.println("Access failed");
		return -1;
	}

	public void printMiddle() {
		Node slowPointer = head;
		Node fastPointer = head;
		if (head != null) {
			while (fastPointer != null && fastPointer.next != null) {
				fastPointer = fastPointer.next.next;
				slowPointer = slowPointer.next;
			}
			System.out.println("The middle element is [" + slowPointer.data + "]");
		}
	}

	/**
	 * Given a Linked List and a number n, write a function that returns the value at the n’th node from end of the Linked List.<br/>
	 * <b>Method 1 (Use length of linked list)</b><br/>
	 * 1) Calculate the length of Linked List. Let the length be len.<br/>
	 * 2) Print the (len – n + 1)th node from the begining of the Linked List.
	 */
	public void printNthFromLast(final int n) {
		int len = 0;
		Node temp = head;
		while (temp != null) {
			temp = temp.next;
			len++;
		}
		if (len < n) {
			return;
		}
		temp = head;
		int loop = len - n + 1;
		for (int i = 1; i < loop; i++) {
			temp = temp.next;
		}
		System.out.println(temp.data);
	}

	public void deleteList() {
		head = null;
	}

	/**
	 * Given a singly linked list and a key, count number of occurrences of given key in linked list. For example, if given linked list is
	 * 1->2->1->2->1->3->1 and given key is 1, then output should be 4. <br/>
	 * <b>Algorithm: </b><br/>
	 * 1. Initialize count as zero.<br/>
	 * 2. Loop through each element of linked list:<br/>
	 * a) If element data is equal to the passed number then increment the count.<br/>
	 * 3. Return count.
	 */
	public int count(final int searchFor) {
		Node current = head;
		int count = 0;
		while (current != null) {
			if (current.data == searchFor) {
				count++;
			}
			current = current.next;
		}
		return count;
	}

	/**
	 * Given a linked list, check if the the linked list has loop or not. Below diagram shows a linked list with a loop. <b>Floyd’s Cycle-Finding
	 * Algorithm:</b><br />
	 * This is the fastest method. Traverse linked list using two pointers. Move one pointer by one and other pointer by two. If these pointers meet
	 * at some node then there is a loop. If pointers do not meet then linked list doesn’t have loop.
	 */
	public int detectLoop() {
		Node slow = head;
		Node fast = head;
		while (slow != null && fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				System.out.println("Found loop");
				return 1;
			}
		}
		return 0;
	}

	public void sortedInsert(final Node n) {
		if (head == null || head.data >= n.data) {
			n.next = head;
			head = n;
		} else {
			Node current = head;
			while (current.next != null && current.next.data < n.data) {
				current = current.next;
			}
			n.next = current.next;
			current.next = n;
		}
	}

	public void printReverse(final Node head) {
		if (head == null) {
			return;
		}
		printReverse(head.next);
		System.out.print(head.data + "->");
	}

	public void removeDuplicatesFromSortedList() {
		if (head == null) {
			return;
		}
		Node current = head;
		Node next = null;
		while (current.next != null) {
			if (current.data == current.next.data) {
				next = current.next.next;
				current.next = null;
				current.next = next;
			} else {
				current = current.next;
			}
		}
	}

	public void removeDuplicatesFromUnsortedList() {
		if (head == null) {
			return;
		}
		Node current = head;
		Node next = null;
		while (current != null && current.next != null) {
			next = current;
			while (next.next != null) {
				if (current.data == next.next.data) {
					next.next = next.next.next;
				} else {
					next = next.next;
				}
			}
			current = current.next;
		}
	}

	// public int findByIndex(int k) {
	// if (head == null) { return -1; }
	// Node current = head;
	// int count = 0;
	// while (current != null && current.next != null) {
	// if (count == k) {
	// return current.data;
	// } else {
	// current = current.next;
	// }
	// count++;
	// }
	// if(k>count) {
	// System.out.println("Wrong index");
	// }
	// return -1;
	// }
	public int findByIndex(final int k) {
		if (head == null) {
			return -1;
		}
		Node current = head;
		for (int i = 0; i < k; i++) {
			if ((current == null || current.next == null) && i < k) {
				System.out.println("Wrong index");
				return -1;
			}
			current = current.next;
		}
		return current.data;
	}

	public void moveToFront() {
		if (head == null || head.next == null) {
			return;
		}
		Node secLast = null;
		Node last = head;
		while (last.next != null) {
			secLast = last;
			last = last.next;
		}
		secLast.next = null;
		last.next = head;
		head = last;
	}

	public Node getTail() {
		return traverse(head);
	}

	public void partitionByKey(final int key) {
		if (head == null) {
			return;
		}

		Node current = head;
		LinkedList beforeList = new LinkedList();
		LinkedList afterList = new LinkedList();

		while (current != null && current.next != null) {
			if (current.data > key) {
				afterList.push(current.data);
			} else {
				beforeList.push(current.data);
			}
			current = current.next;
		}

		if (current.data > key) {
			if (current.data > key) {
				afterList.push(current.data);
			} else {
				beforeList.push(current.data);
			}
		}

		// afterList.printList();
		beforeList.printList();

		beforeList.getTail().next = afterList.head;
		beforeList.printList();
	}

	public boolean isPalindrome() {
		Node slow = head;
		Node fast = head;
		Stack<Integer> stack = new Stack<Integer>();
		while (fast != null && fast.next != null) {
			stack.push(slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast != null) {
			slow = slow.next;
		}
		while (slow != null) {
			int top = stack.pop().intValue();
			if (top != slow.data) {
				return false;
			}
			slow = slow.next;
		}
		return true;
	}

	public int size() {
		Node current = head;
		int count = 0;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	public void sortByOddAndEven() {
		if (head == null) {
			return;
		}
		Node odd = head;
		Node even = head.next;
		Node evenHead = even;
		while (even != null && even.next != null) {
			odd.next = even.next;
			odd = odd.next;
			even.next = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
	}

	public static void main(final String[] args) {
		// testDeleteNode();
		// testSwapNodes();
		// testGetNth();
		// testDeleteNodeByNode();
		// testPrintMiddle();
		// testPrintNthFromLast();
		// testDetectLoop();
		// testSortedInsert();
		// testRemoveDuplicatesFromUnsortedList();
		// testMoveToFront();
		// testFindByIndex();
		// testPartitionByKey();
		// testIsPalindrome();
		testSortByOddAndEven();
	}

	static void testSortByOddAndEven() {
		LinkedList list = initList(Type.SORTED_DESC);

		System.out.print("Before: ");
		list.printList();
		// System.out.println(list.size());
		list.sortByOddAndEven();

		System.out.println();
		System.out.print("After: ");
		list.printList();
	}

	static void testIsPalindrome() {
		LinkedList list = new LinkedList();
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(2);
		list.push(1);
		list.printList();
		System.out.println(list.isPalindrome());
	}

	static void testPartitionByKey() {
		LinkedList list = initList(Type.UNSORTED);
		list.partitionByKey(50);
	}

	static void testFindByIndex() {
		LinkedList list = initList(Type.SORTED_ASC);
		list.printList();
		System.out.println(list.findByIndex(4));
	}

	static void testMoveToFront() {
		LinkedList list = initList(Type.SORTED_DESC);
		list.printList();
		list.moveToFront();
		list.printList();
	}

	static void testRemoveDuplicatesFromUnsortedList() {
		LinkedList list = initList(Type.UNSORTED, true);
		list.printList();
		list.removeDuplicatesFromUnsortedList();
		list.printList();
	}

	static void testSortedInsert() {
		LinkedList list = new LinkedList();
		list.sortedInsert(list.new Node(5));
		list.sortedInsert(list.new Node(10));
		list.sortedInsert(list.new Node(7));
		list.sortedInsert(list.new Node(9));
		list.sortedInsert(list.new Node(1));
		list.sortedInsert(list.new Node(8));
		list.printList();
	}

	static void testDetectLoop() {
		LinkedList list = initList(Type.UNSORTED);
		list.head.next.next.next.next.next.next.next.next.next = list.head;
		list.detectLoop();
	}

	static void testPrintNthFromLast() {
		LinkedList list = initList(Type.UNSORTED);
		list.printList();
		list.printNthFromLast(4);
	}

	static void testPrintMiddle() {
		LinkedList list = new LinkedList();
		for (int i = 5; i > 0; --i) {
			list.push(i);
			list.printList();
			list.printMiddle();
		}
	}

	static void testDeleteNodeByNode() {
		LinkedList list = initList(Type.UNSORTED);
		System.out.println("Before deleting");
		list.printList();
		list.deleteNodeByNode(list.head);
		System.out.println("After deleting");
		list.printList();
	}

	static void testGetNth() {
		LinkedList list = initList(Type.UNSORTED);
		list.printList();
		System.out.println("\nElement at index 3 is " + list.getNth(3));
	}

	static void testSwapNodes() {
		LinkedList list = initList(Type.SORTED_DESC);

		System.out.print("\n Linked list before calling swapNodes() ");
		list.printList();

		list.swapNodes(4, 3);

		System.out.print("\n Linked list after calling swapNodes() ");
		list.printList();
	}

	static void testDeleteNode() {
		LinkedList list = new LinkedList();
		list.append(6);
		list.push(7);
		list.push(1);
		list.append(4);
		list.insertAfter(list.head.next, 8);
		System.out.println("\nCreated Linked list is: ");
		list.printList();

		list.deleteNode(8);
		list.printList();
	}

	private enum Type {
		SORTED_DESC, SORTED_ASC, UNSORTED;
	}

	private static LinkedList initList(final Type type) {
		return initList(type, true);
	}

	private static LinkedList initList(final Type type, final boolean haveDuplicate) {
		LinkedList list = new LinkedList();
		int size = 10;
		if (Type.SORTED_DESC == type) {
			for (int i = size; i >= 1; i--) {
				list.push(i);
			}
		} else if (Type.SORTED_ASC == type) {
			for (int i = 1; i < size; i++) {
				list.push(i);
			}
		} else if (Type.UNSORTED == type) {
			Random random = new Random();
			if (!haveDuplicate) {
				Set<Integer> set = new HashSet<Integer>();
				for (int i = 1; i < size; i++) {
					set.add(Integer.valueOf(random.nextInt(50)));
				}
				for (Integer n : set) {
					list.push(n.intValue());
				}
			} else {
				for (int i = 1; i < size; i++) {
					list.push(Integer.valueOf(random.nextInt(100)));
				}
			}
		}
		return list;
	}
}