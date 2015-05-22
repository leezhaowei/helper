package com.zwli.algorithm;

public class CustomLinkedList<T> {

    private Node<T> head;

    private Node<T> tail;

    public CustomLinkedList() {
    }

    public Node<T> head() {
        return head;
    }

    public void add(Node<T> node) {
        if (null == head && null == tail) {
            head = tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }
}

class Node<T> {

    private Node<T> next;

    private T data;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> next() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
