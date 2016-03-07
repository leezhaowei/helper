package com.zwli.datastructure.linkedlist;

public class DoublyLink<E> {

    public E data;
    public DoublyLink<E> next;
    public DoublyLink<E> previous;

    public DoublyLink(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
