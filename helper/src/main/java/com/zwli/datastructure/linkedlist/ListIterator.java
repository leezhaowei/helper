package com.zwli.datastructure.linkedlist;

public interface ListIterator<E> {

    public void reset();

    public boolean atEnd();

    public void nextLink();

    public Link<E> getCurrent();

    public void insertAfter(E data);

    public void insertBefore(E data);

    public E deleteCurrent();
}
