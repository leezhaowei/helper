package com.zwli.datastructure.binarytree;

public interface ITree {

    public void insert(int data);

    public void displayTree();

    public TreeNode find(int data);

    public boolean delete(int data);

    public void inOrder(TreeNode root);
}
