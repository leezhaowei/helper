package com.zwli.algorithm;

/**
 * The are a group of nodes linked together by some reference. Singly linked list has reference to next node, while
 * doubly linked list has reference to previous and next nodes. Elements can be inserted indefinitely. <br>
 * They don’t take contiguous memory locations. For this reason, they are suitable to be used even if the disk or memory
 * is fragmented. <br>
 * They allow sequential access to elements while arrays allow random access <br>
 * Extra space is needed for reference. It is impractical to use for boolean or char for space wastage. <br>
 * In this article, I have provided solutions coding for the following: <br>
 * <br>
 *
 * Reversing a singly linked list by using recursion – Recursive Reverse <br>
 * Reversing a singly linked list by using iteration – Iterative Reverse. <br>
 */
public class LinkedListReverse {

    public static void main(String args[]) {
        // Preparing some linked list structure
        LinkedList linkedList = new LinkedList(5);
        linkedList.next = new LinkedList(4);
        linkedList.next.next = new LinkedList(3);
        linkedList.next.next.next = new LinkedList(2);
        linkedList.next.next.next.next = new LinkedList(1);

        System.out.println("Original Linked List: " + linkedList.toString());

        // recursively reverse and print
        linkedList = recursiveReverse(linkedList);
        System.out.println("Recursively Reversed List: " + linkedList.toString());

        // iteratively reverse and print
        linkedList = iterativeReverse(linkedList);
        System.out.println("Iteratively Recursed to Original: " + linkedList.toString());
    }

    /**
     * This method uses recursive method to reverse a singly linked list.
     */
    public static LinkedList recursiveReverse(LinkedList linkedList) {
        // check for empty or size 1 linked list. This is a base condition to
        // terminate recursion.
        if (linkedList == null || linkedList.next == null) {
            return linkedList;
        }

        LinkedList remainingReverse = recursiveReverse(linkedList.next);

        // update the tail as beginning
        LinkedList current = remainingReverse;
        while (current.next != null) {
            current = current.next;

        }
        // assign the head as a tail
        current.next = linkedList;
        linkedList.next = null;

        return remainingReverse;
    }

    /**
     * This method uses iterative approach to reverse a singly linked list.
     */
    public static LinkedList iterativeReverse(LinkedList linkedList) {
        if (linkedList == null || linkedList.next == null) {
            return linkedList;
        }

        LinkedList prevNode, currNode, nextNode;
        prevNode = null;
        nextNode = null;
        currNode = linkedList;

        while (currNode != null) {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        return prevNode;
    }

    private static class LinkedList {

        public LinkedList next;

        public int value;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            String data = "";
            LinkedList current = this;
            do {
                data += current.value + ",";
                current = current.next;
            } while (current != null);

            return data;
        }
    }
}
