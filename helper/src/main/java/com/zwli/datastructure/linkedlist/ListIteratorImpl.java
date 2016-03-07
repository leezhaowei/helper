package com.zwli.datastructure.linkedlist;

public class ListIteratorImpl<E> implements ListIterator<E> {

    private Link<E> current;
    private Link<E> previous;
    private LinkList<E> list;

    public ListIteratorImpl(LinkList<E> list) {
        this.list = list;
        reset();
    }

    @Override
    public void reset() {
        current = list.getHead();
        previous = null;
    }

    @Override
    public boolean atEnd() {
        return current.next == null;
    }

    @Override
    public void nextLink() {
        previous = current;
        current = current.next;
    }

    @Override
    public Link<E> getCurrent() {
        return current;
    }

    @Override
    public void insertAfter(E data) {
        Link<E> newLink = new Link<E>(data);
        if (list.isEmpty()) {
            list.setHead(newLink);
            current = newLink;
        } else {
            newLink.next = current.next;
            current.next = newLink;
            nextLink();
        }
    }

    @Override
    public void insertBefore(E data) {
        Link<E> newLink = new Link<E>(data);
        if (previous == null) {
            newLink.next = list.getHead();
            list.setHead(newLink);
            reset();
        } else {
            newLink.next = previous.next;
            previous.next = newLink;
            current = newLink;
        }
    }

    @Override
    public E deleteCurrent() {
        // TODO Auto-generated method stub
        return null;
    }

}
