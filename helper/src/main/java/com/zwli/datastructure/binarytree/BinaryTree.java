package com.zwli.datastructure.binarytree;

public interface BinaryTree<T extends Comparable<T>> {

    public void insert(T data);

    public void displayTree();

    public Node<T> find(T data);

    public boolean delete(T data);

    public void inOrder(Node<T> root);

    public int size();

    public Node<T> findMinNode();

    public int maxDepth(final Node<T> current);

}
