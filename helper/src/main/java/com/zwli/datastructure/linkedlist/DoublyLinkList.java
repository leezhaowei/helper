package com.zwli.datastructure.linkedlist;

public interface DoublyLinkList<E> {

    public boolean isEmpty();

    public void insertHead(E data);

    public void insertRear(E data);

    public boolean insertAfter(E key, E data);

    public DoublyLink<E> deleteHead();

    public DoublyLink<E> deleteRear();

    public DoublyLink<E> deleteKey(E key);

    public void displayForward();

    public void displayBackward();
}
