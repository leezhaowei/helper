package com.zwli.datastructure.linkedlist;

public class HeadRearListImpl<E> extends LinkListImpl<E> implements HeadRearList<E> {
    protected Link<E> rear;

    public HeadRearListImpl() {
        head = null;
        rear = null;
    }

    @Override
    public void insertHead(final E data) {
        Link<E> newLink = new Link<E>(data);
        if (isEmpty()) {
            rear = newLink;
        }
        newLink.next = head;
        head = newLink;
    }

    @Override
    public void insertRear(final E data) {
        Link<E> newLink = new Link<E>(data);
        if (isEmpty()) {
            head = newLink;
        } else {
            rear.next = newLink;
        }
        rear = newLink;
    }

    @Override
    public Link<E> deleteHead() {
        Link<E> tmp = head;
        if (head.next == null) {
            rear = null;
        }
        head = head.next;
        return tmp;
    }

    public static void main(final String[] args) {
        HeadRearListImpl<Integer> htl = new HeadRearListImpl<Integer>();
        htl.insertHead(22);
        htl.insertHead(33);
        htl.insertHead(44);
        htl.insertRear(77);
        htl.insertRear(88);
        htl.insertRear(99);
        System.out.print("List (head --> tail): ");
        htl.displayList();

        htl.deleteHead();
        htl.deleteHead();
        System.out.print("List (head --> tail): ");
        htl.displayList();
    }
}
