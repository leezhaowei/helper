package com.zwli.datastructure.linkedlist;

public interface SortedList<E extends Comparable<? super E>> extends LinkList<E> {

    public void insert(E data);

    public Link<E> remove();
}
