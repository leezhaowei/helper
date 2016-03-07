package com.zwli.datastructure.linkedlist;

public class Link<E> {
    public E data;
    public Link<E> next;

    public Link(final E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
