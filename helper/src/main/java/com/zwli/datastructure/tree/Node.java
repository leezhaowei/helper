package com.zwli.datastructure.tree;

public class Node<T> {

    public int key;
    public T data;
    public Node<T> left;
    public Node<T> right;

    public void displayNode() {
        StringBuilder info = new StringBuilder();
        info.append('{');
        info.append(key);
        info.append(", ");
        info.append(data);
        info.append("} ");
        System.out.println(info.toString());
    }
}
