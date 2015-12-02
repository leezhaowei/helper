package com.zwli.datastructure.linkedlist;

public class LinkedList {
    Node head;

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public void push(int d) {
        Node n = new Node(d);
        n.next = head;
        head = n;
    }

    public void insertAfter(Node prevNode, int d) {
        if (null == prevNode) {
            System.out.println("The given previous node cannot null!");
            return;
        }
        Node n = new Node(d);
        n.next = prevNode.next;
        prevNode.next = n;
    }

    public void append(int d) {
        Node n = new Node(d);
        if (null == head) {
            head = n;
            return;
        }
        n.next = null;
        Node last = traverse(head);
        last.next = n;
    }

    public void printList() {
        System.out.println();
        Node n = head;
        while (null != n) {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }

    private Node traverse(Node last) {
        while (null != last.next) {
            last = last.next;
        }
        return last;
    }

    public void deleteNode(int key) {
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

    public void deleteNodeByPosition(int position) {
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

    public void swapNodes(int x, int y) {
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

    public static void main(String[] args) {
        // LinkedList llist = new LinkedList();
        // llist.append(6);
        // llist.push(7);
        // llist.push(1);
        // llist.append(4);
        // llist.insertAfter(llist.head.next, 8);
        // System.out.println("\nCreated Linked list is: ");
        // llist.printList();
        //
        // llist.deleteNode(8);
        // llist.printList();

        testSwapNodes();
    }

    static void testSwapNodes() {
        LinkedList llist = new LinkedList();

        /*
         * The constructed linked list is: 1->2->3->4->5->6->7
         */
        llist.push(7);
        llist.push(6);
        llist.push(5);
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);

        System.out.print("\n Linked list before calling swapNodes() ");
        llist.printList();

        llist.swapNodes(4, 3);

        System.out.print("\n Linked list after calling swapNodes() ");
        llist.printList();
    }
}
