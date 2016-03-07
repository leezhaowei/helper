package com.zwli.datastructure.linkedlist;

public class DoublyLinkListImpl<E> implements DoublyLinkList<E> {

    private DoublyLink<E> head;
    private DoublyLink<E> rear;

    public DoublyLinkListImpl() {
        head = null;
        rear = null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void insertHead(E data) {
        DoublyLink<E> newLink = new DoublyLink<E>(data);
        if (isEmpty()) {
            rear = newLink;
        } else {
            head.previous = newLink;
        }
        newLink.next = head;
        head = newLink;
    }

    @Override
    public void insertRear(E data) {
        DoublyLink<E> newLink = new DoublyLink<E>(data);
        if (isEmpty()) {
            head = newLink;
        } else {
            rear.next = newLink;
            newLink.previous = rear;
        }
        rear = newLink;
    }

    @Override
    public boolean insertAfter(E key, E data) {
        if (isEmpty()) {
            return false;
        }

        DoublyLink<E> current = head;
        while (!current.data.equals(key)) {
            current = current.next;
            if (current == null) {
                return false;
            }
        }
        DoublyLink<E> newLink = new DoublyLink<E>(data);
        if (current == rear) {
            newLink.next = null;
            rear = newLink;
        } else {
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        newLink.previous = current;
        current.next = newLink;
        return true;
    }

    @Override
    public DoublyLink<E> deleteHead() {
        if (isEmpty()) {
            return null;
        }
        DoublyLink<E> tmp = head;
        if (head.next == null) {
            head = null;
        } else {
            head.next.previous = null;
        }
        head = head.next;
        return tmp;
    }

    @Override
    public DoublyLink<E> deleteRear() {
        if (isEmpty()) {
            return null;
        }
        DoublyLink<E> tmp = rear;
        if (head.next == null) {
            head = null;
        } else {
            rear.previous.next = null;
        }
        rear = rear.previous;
        return tmp;
    }

    @Override
    public DoublyLink<E> deleteKey(E key) {
        DoublyLink<E> current = head;
        while (!current.data.equals(key)) {
            current = current.next;
            if (current == null) {
                return null;
            }
        }
        if (current == head) {
            head = current.next;
        } else {
            current.previous.next = current.next;
        }

        if (current == rear) {
            rear = current.previous;
        } else {
            current.next.previous = current.previous;
        }
        return current;
    }

    @Override
    public void displayForward() {
        DoublyLink<E> current = head;
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public void displayBackward() {
        DoublyLink<E> current = rear;
        while (current != null) {
            System.out.print(current + " ");
            current = current.previous;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkList<Integer> list = new DoublyLinkListImpl<Integer>();
        list.insertHead(22);
        list.insertHead(44);
        list.insertHead(66);

        list.insertRear(11);
        list.insertRear(33);
        list.insertRear(55);

        System.out.print("List (head --> rear): ");
        list.displayForward();
        System.out.print("List (rear --> head): ");
        list.displayBackward();

        list.deleteHead();
        list.deleteRear();
        list.deleteKey(11);
        System.out.print("List (head --> rear): ");
        list.displayForward();

        list.insertAfter(22, 77);
        list.insertAfter(33, 88);
        System.out.print("List (head --> rear): ");
        list.displayForward();
    }
}
