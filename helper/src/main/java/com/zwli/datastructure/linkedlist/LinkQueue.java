package com.zwli.datastructure.linkedlist;

public class LinkQueue<E> {

    private final HeadRearList<E> list;

    public LinkQueue() {
        list = new HeadRearListImpl<E>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void insert(final E data) {
        list.insertRear(data);
    }

    public E remove() {
        return list.deleteHead().data;
    }

    public void displayQueue() {
        System.out.print("Queue (front --> rear): ");
        list.displayList();
    }

    public static void main(final String[] args) {
        LinkQueue<Integer> queue = new LinkQueue<Integer>();
        queue.insert(22);
        queue.insert(44);
        queue.displayQueue();

        queue.insert(33);
        queue.insert(55);
        queue.displayQueue();

        queue.remove();
        queue.remove();
        queue.displayQueue();
    }
}
