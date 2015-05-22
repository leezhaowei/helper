package com.zwli.datastructure.binarytree;

public class TreeNode {

    protected int data;

    protected TreeNode left;

    protected TreeNode right;

    public TreeNode(int data) {
        super();
        this.data = data;
    }

    public void displayNode() {
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
