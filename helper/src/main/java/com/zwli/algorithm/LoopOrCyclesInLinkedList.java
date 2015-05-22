package com.zwli.algorithm;

/**
 * Two pointers, fast and slow is used while iterating over linked list. Fast pointer moves two nodes in each iteration,
 * while slow pointer moves to one node. If linked list contains loop or cycle than both fast and slow pointer will meet
 * at some point during iteration. If they don't meet and fast or slow will point to null, then linked list is not
 * cyclic and it doesn't contain any loop. Here is exact algorithm <br>
 * <br>
 * 1) Use two pointers fast and slow <br>
 * 2) Move fast two nodes and slow one node in each iteration <br>
 * 3) If fast and slow meet then linked list contains cycle <br>
 * 4) if fast points to null or fast.next points to null then linked list is not cyclic <br>
 */
public class LoopOrCyclesInLinkedList {

    public static void main(String args[]) {
        // creating LinkedList with 5 elements including head
        LinkedList linkedList = new LinkedList();
        linkedList.appendIntoTail(new LinkedList.Node("101"));
        linkedList.appendIntoTail(new LinkedList.Node("201"));
        linkedList.appendIntoTail(new LinkedList.Node("301"));
        linkedList.appendIntoTail(new LinkedList.Node("401"));

        System.out.println("Linked List : " + linkedList);

        if (linkedList.isCyclic()) {
            System.out.println("Linked List is cyclic as it contains cycles or loop");
        } else {
            System.out.println("LinkedList is not cyclic, no loop or cycle found");
        }

    }

    static class LinkedList {

        private Node head;

        public LinkedList() {
            this.head = new Node("head");
        }

        public Node head() {
            return head;
        }

        public void appendIntoTail(Node node) {
            Node current = head;

            // find last element of LinkedList i.e. tail
            while (current.next() != null) {
                current = current.next();
            }
            // appending new node to tail in LinkedList
            current.setNext(node);
        }

        /**
         * If singly LinkedList contains Cycle then following would be true <br>
         * 1) slow and fast will point to same node i.e. they meet On the other hand if fast will point to null or next
         * node of fast will point to null then LinkedList does not contains cycle.
         */
        public boolean isCyclic() {
            Node fast = head;
            Node slow = head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;

                // if fast and slow pointers are meeting then LinkedList is cyclic
                if (fast == slow) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node current = head.next();
            while (current != null) {
                sb.append(current).append("-->");
                current = current.next();
            }
            sb.delete(sb.length() - 3, sb.length()); // to remove --> from last node
            return sb.toString();
        }

        public static class Node {

            private Node next;

            private String data;

            public Node(String data) {
                this.data = data;
            }

            public String data() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public Node next() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            @Override
            public String toString() {
                return this.data;
            }
        }
    }
}
