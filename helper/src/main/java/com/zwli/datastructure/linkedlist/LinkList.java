package com.zwli.datastructure.linkedlist;

public interface LinkList<E> {

    public boolean isEmpty();

    public void displayList();

    public void insertHead(E data);

    public Link<E> deleteHead();

    public Link<E> find(E key);

    public Link<E> delete(E key);

    public Link<E> getHead();

    public void setHead(Link<E> head);

    public ListIterator<E> iterator();
}
