package com.zwli.datastructure.binarytree;

public class Node<T extends Comparable<T>> {

    public T data;
    public Node<T> left;
    public Node<T> right;

    public Node(final T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }

    public void displayNode() {
        System.out.println(String.valueOf(data));
    }
}
