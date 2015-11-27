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

    public static void main(String[] args) {
        LinkedList llist = new LinkedList();
        llist.append(6);
        llist.push(7);
        llist.push(1);
        llist.append(4);
        llist.insertAfter(llist.head.next, 8);
        System.out.println("\nCreated Linked list is: ");
        llist.printList();
    }
}
