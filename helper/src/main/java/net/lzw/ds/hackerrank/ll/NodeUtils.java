package net.lzw.ds.hackerrank.ll;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.lzw.ds.hackerrank.ll.NodeGenerator.NodeType;

public class NodeUtils {

	static void log(Node node) {
		printNode(node);
	}

	static void log(String msg) {
		System.out.println(msg);
	}

	public static Node sortedInsert(Node head, int data) {
		Node node = new Node(data);
		return sortedInsert(head, node);
	}

	public static Node sortedInsert(Node head, Node node) {
		if (head == null || head.data >= node.data) {
			node.next = head;
			return node;
		} else {
			Node current = head;
			while (current.next != null && current.next.data < node.data) {
				current = current.next;
			}
			node.next = current.next;
			current.next = node;
			return head;
		}
	}

	public static Node insertInFront(Node head, Integer data) {
		Node node = new Node(data);
		node.next = head;
		return node;
	}

	public static Node search(Node head, Integer data) {
		while (head != null && head.data != data) {
			head = head.next;
		}
		return head;
	}

	public static boolean hasCycle(Node head) {
		Node fast = head;
		Node slow = head;

		while (fast != null && slow != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	public static void printNode(Node head) {
		if (head == null) {
			System.out.println("Empty node.");
			return;
		}

		Node current = head;
		do {
			System.out.print(current.data);
			current = current.next;
			if (current != null) {
				System.out.print(" -> ");
			}
		} while (current != null && current.next != null);
		if (current != null) {
			System.out.println(current.data);
		} else {
			System.out.println();
		}
	}

	public static Node sortedMerge(Node heada, Node headb) {
		if (heada == null && headb == null) {
			return null;
		}
		if (heada == null) {
			return headb;
		}
		if (headb == null) {
			return heada;
		}

		Node current = null;
		Node merged = null;
		while (heada != null && headb != null) {
			if (heada.data <= headb.data) {
				if (current == null) {
					current = heada;
					merged = heada;
				} else {
					current.next = heada;
					System.out.println("LESS 1");
					print(heada, headb, current, merged);
					current = current.next;
				}
				heada = heada.next;
				System.out.println("LESS 2");
				print(heada, headb, current, merged);
			} else {
				if (current == null) {
					current = headb;
					merged = headb;
				} else {
					current.next = headb;
					System.out.println("GREATER 1");
					print(heada, headb, current, merged);
					current = current.next;
				}
				headb = headb.next;
				System.out.println("GREATER 2");
				print(heada, headb, current, merged);
			}
		}
		System.out.println("Out of loop...");
		print(heada, headb, current, merged);
		if (heada != null) {
			current.next = heada;
		}
		print(heada, headb, current, merged);
		if (headb != null) {
			current.next = headb;
		}
		print(heada, headb, current, merged);
		return merged;
	}

	private static void print(Node heada, Node headb, Node current, Node merged) {
		System.out.print("heada   = ");
		printNode(heada);
		System.out.print("headb   = ");
		printNode(headb);
		System.out.print("current = ");
		printNode(current);
		System.out.print("merged  = ");
		printNode(merged);
		System.out.println("-------------------");
	}

	public static Node merge(Node heada, Node headb) {
		if (heada == null && headb == null) {
			return null;
		}
		if (heada == null) {
			return headb;
		}
		if (headb == null) {
			return heada;
		}
		Node node = null;
		if (heada.data <= headb.data) {
			node = heada;
			node.next = merge(heada.next, headb);
		} else {
			node = headb;
			node.next = merge(heada, headb.next);
		}
		return node;
	}

	public static Node reverse(Node head) {
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

	public static Node getMiddle(Node head) {
		Node slow = head;
		Node fast = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	public static boolean isPalindrome(Node head) {
		printNode(head);
		Node current = getMiddle(head);
		printNode(current);
		current = reverse(current);
		printNode(current);
		boolean result = true;
		while (head != null && current != null) {
			if (head.data != current.data) {
				result = false;
			}
			head = head.next;
			current = current.next;
		}
		return result;
	}

	public static int intersectPoint(Node heada, Node headb) {
		if (heada == null || headb == null) {
			throw new IllegalArgumentException();
		}
		Node current = headb;
		while (heada != null) {
			while (current != null) {
				if (heada.data == current.data) {
					return current.data;
				}
				current = current.next;
			}
			heada = heada.next;
			current = headb;
		}
		return -1;
	}

	public static Node removeDuplicates(Node head) {
		Node current = head;
		while (current != null && current.next != null) {
			if (current.data == current.next.data) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
		return head;
	}

	public static Node removeDuplicatesOfUnsorted(Node head) {
		Node current = head;
		Node forward = null;
		while (current != null && current.next != null) {
			forward = current;
			while (forward.next != null) {
				if (current.data == forward.next.data) {
					forward.next = forward.next.next;
				} else {
					forward = forward.next;
				}
			}
			current = current.next;
		}
		return head;
	}

	public static Node moveLastToFront(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		log(head);
		Node previous = null;
		Node last = head;
		while (last.next != null) {
			previous = last;
			last = last.next;
		}
		previous.next = null;
		last.next = head;
		head = last;

		return head;
	}

	// --- getIntersection ---
	private static class Intersect {
		Node head; // head of list
	}

	private static Intersect llist3 = new Intersect();

	public static void getIntersection(Node heada, Node headb) {
		if (heada == null || headb == null) {
			return;
		}
		Node nodea = heada;
		Node nodeb = null;
		Set<Integer> set = new TreeSet<>();
		while (nodea != null) {
			nodeb = headb;
			while (nodeb != null) {
				if (nodea.data == nodeb.data) {
					log(nodea.data + "");
					set.add(nodea.data);
				}
				nodeb = nodeb.next;
			}
			nodea = nodea.next;
		}

		Node current = null;
		for (int data : set) {
			Node node = new Node(data);
			if (llist3.head == null) {
				llist3.head = node;
			} else {
				current = llist3.head;
				while (current.next != null) {
					current = current.next;
				}
				current.next = node;
			}
		}

		log(heada);
		log(headb);
		log(llist3.head);
	}
	// --- getIntersection ---

	public static Node insertNth(Node head, int data, int position) {
		Node node = new Node();
		node.data = data;
		if (head == null) {
			head = node;
			return head;
		}
		if (position == 0) {
			node.next = head;
			head = node;
			return head;
		}
		int index = 1;
		Node current = head.next;
		Node previous = head;
		while (current != null) {
			if (index == position) {
				node.next = current;
				previous.next = node;
				break;
			}
			index++;
			previous = previous.next;
			current = current.next;
		}
		return head;
	}

	public static Node deleteNth(Node head, int position) {
		if (head == null) {
			return null;
		}
		if (position == 0) {
			head = head.next;
			return head;
		}
		int index = 1;
		Node previous = head;
		Node current = head.next;
		while (current != null) {
			if (index == position) {
				previous.next = current.next;
				break;
			}
			index++;
			previous = previous.next;
			current = current.next;
		}
		return head;
	}

	public static void reversePrint(Node head) {
		if (head == null) {
			return;
		}
		Node previous = null;
		Node current = head;
		Node forward = null;
		while (current != null) {
			forward = current.next;
			current.next = previous;
			previous = current;
			current = forward;
		}
		head = previous;
		current = head;
		while (current != null) {
			System.out.println(current.data);
			current = current.next;
		}
	}

	public static int compareLists(Node heada, Node headb) {
		if (heada == null && headb == null) {
			return 1;
		}
		while (heada != null && headb != null) {
			if (heada.data != headb.data) {
				return 0;
			}
			heada = heada.next;
			headb = headb.next;
		}
		if (heada == null && headb == null) {
			return 1;
		}
		return 0;
	}

	public static int getNodeFromTail(Node head, int n) {
		Node previous = null;
		Node current = head;
		Node forward = null;
		while (current != null) {
			forward = current.next;
			current.next = previous;
			previous = current;
			current = forward;
		}
		// log(previous);
		int index = 0;
		current = previous;
		while (current != null) {
			if (index == n) {
				return current.data;
			}
			index++;
			current = current.next;
		}
		return -1;
	}

	public static int findMergeNode(Node heada, Node headb) {
		Node current = null;
		while (heada != null) {
			current = headb;
			while (current != null) {
				if (heada == current) {
					return current.data;
				}
				current = current.next;
			}
			heada = heada.next;
		}
		return -1;
	}

	public static Node sortedInsertDoublyList(Node head, int data) {
		Node node = new Node();
		node.data = data;
		if (head == null) {
			head = node;
			return head;
		}
		Node current = head;
		while (current != null) {
			if (current.data < data) {
				if (current.next == null) {
					current.next = node;
					node.prev = current;
					break;
				}
				current = current.next;
			} else {
				node.next = current;
				node.prev = current.prev;
				current.prev.next = node;
				current.prev = node;
				break;
			}
		}
		return head;
	}

	public static Node reverseDoublyList(Node head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return head;
		}
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		head = current;
		while (current != null) {
			Node next = current.prev;
			Node prev = current.next;
			current.next = next;
			current.prev = prev;
			current = current.next;
		}
		return head;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		// testMerge();
		// testPalindrome();
		// testMiddle();
		// testIntersectPoint();
		// testRemoveDuplicates();
		// testRemoveDuplicatesOfUnsorted();
		// testMoveLastToFront();
		// testGetIntersection();
		// testDeleteNth();
		// testReversePrint();
		// testCompareLists();
		// testMergeLists();
		// testGetNodeFromTail();
		// testFindMergeNode();
		// testSortedInsertDoublyLists();
		testReverseDoublyList();

		long end = System.currentTimeMillis() - start;
		System.out.println("\nTime: " + end);
	}

	static void testReverseDoublyList() {
		Node head = NodeGenerator.generateDoublyNode("1 2 3 4 5");
		log(head);
		head = reverseDoublyList(head);
		log(head);
	}

	static void testSortedInsertDoublyLists() {
		Node head = NodeGenerator.generateDoublyNode("1 3 5");
		log(head);
		head = null;
		int data = 2;
		head = sortedInsertDoublyList(head, data);
		log(head);
	}

	static void testFindMergeNode() {
		Node heada = NodeGenerator.generateNode("1 3 5");
		Node headb = NodeGenerator.generateNode("2 4 6");
		Node node = NodeGenerator.generateNode("7 8 9");

		Node current = heada;
		while (current.next != null) {
			current = current.next;
		}
		current.next = node;

		current = headb;
		while (current.next != null) {
			current = current.next;
		}
		current.next = node;

		// log(heada);
		// log(headb);
		int r = findMergeNode(heada, headb);
		System.out.println(r);
	}

	static void testGetNodeFromTail() {
		Node head = NodeGenerator.generateNode("1 3 5 7 9");
		log(head);
		int n = 3;
		int r = getNodeFromTail(head, n);
		System.out.println(r);
	}

	static void testMergeLists() {
		Node heada = NodeGenerator.generateNode("1 3 5 7 9");
		Node headb = NodeGenerator.generateNode("2 4 6 8");

		Node head = merge(heada, headb);
		log(head);
	}

	static void testCompareLists() {
		Node heada = NodeGenerator.generateNode("1 2 3 4 6");
		Node headb = NodeGenerator.generateNode("2 2 4 6 8");
		headb = NodeGenerator.generateNode("1 2 3 4 6 5");

		int r = compareLists(heada, headb);
		System.out.println(r);
	}

	static void testReversePrint() {
		Node head = NodeGenerator.generateNode("1 2 3 4 5");
		printNode(head);
		reversePrint(head);
	}

	static void testDeleteNth() {
		Node head = NodeGenerator.generateNode("1 2 3 4 5");
		printNode(head);
		int position = 0;
		position = 2;
		head = deleteNth(head, position);
		printNode(head);
	}

	static void testGetIntersection() {
		Node heada = NodeGenerator.generateNode("1 2 3 4 6");
		Node headb = NodeGenerator.generateNode("2 2 4 6 8");

		heada = NodeGenerator.generateNode(
				"12 23 28 43 44 59 60 68 70 85 88 92 124 125 136 168 171 173 179 199 212 230 277 282 306 314 316 325 328 336 337 363 365 368 369 371 374 387 394 414 422 422 427 430 435 457 493 506 527 531 538 541 546 568 583 585 650 691 730 737 751 764 778 783 785 789 794 803 809 815 847 858 863 863 874 896 916 920 926 927 930 957 981 997");
		headb = NodeGenerator.generateNode(
				"13 20 32 35 61 95 98 98 118 125 150 194 220 227 229 246 271 281 287 302 307 318 341 351 354 369 369 379 380 400 404 435 441 442 445 452 468 482 489 493 498 501 504 529 540 556 568 571 587 587 602 619 620 625 652 653 676 677 684 690 709 710 716 724 730 733 740 755 757 765 765 772 796 797 830 842 847 857 866 872 903 915 922 928 933 966 988");

		getIntersection(heada, headb);
	}

	static void testMoveLastToFront() {
		Node head = NodeGenerator.generateNode("1 2 3 4 5");
		head = moveLastToFront(head);
		log(head);
	}

	static void testRemoveDuplicatesOfUnsorted() {
		Node head = NodeGenerator.generateNode("5 4 5 5 7 3");
		head = removeDuplicatesOfUnsorted(head);
		printNode(head);
	}

	static void testRemoveDuplicates() {
		Node head = NodeGenerator.generateNode("5 2 2 4 1 2 2 4 5 6 6 7");
		head = removeDuplicates(head);
		printNode(head);
	}

	static void testIntersectPoint() {
		int[] arr = new int[] { 3, 6, 9, 15, 30 };
		Node heada = NodeGenerator.generateNode(arr);
		arr = new int[] { 10, 15, 30 };
		Node headb = NodeGenerator.generateNode(arr);
		printNode(heada);
		printNode(headb);

		int is = intersectPoint(heada, headb);
		System.out.println(is);
	}

	static void testMiddle() {
		int size = 9;
		Node head = NodeGenerator.generatePalindrome(size);
		printNode(head);
		getMiddle(head);
		printNode(head);
	}

	static void testPalindrome() {
		int size = 7;
		Node head = NodeGenerator.generatePalindrome(size);
		// head = NodeGenerator.generateNode(size);

		int[] arr = new int[] { 4, 3, 1, 2, 2, 1, 1 };
		head = NodeGenerator.generateNode(arr);

		boolean is = isPalindrome(head);
		System.out.println(is);
	}

	static void testMerge() {
		List<Integer> buf = NodeGenerator.getSeed(10, 0);
		Node heada = NodeGenerator.generate(3, buf, NodeType.ORDER);
		Node headb = NodeGenerator.generate(3, buf, NodeType.ORDER);
		printNode(heada);
		printNode(headb);
		System.out.println("++++++++++++++++++++++++++++");

		Node node = null;
		node = sortedMerge(heada, headb);
		// node = merge(heada, headb);
		printNode(node);
	}

}
