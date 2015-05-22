package com.zwli.datastructure.tree.rb;

public class Node {

    int iData; // data used as key value

    Node parent; // this node's parent

    Node leftChild; // this node's left child

    Node rightChild; // this node's right child

    boolean color; // the false is red and the true is black;

    public void displayNode() {
        // You can define by yourself
    }

    public void setNode(int i) {
        this.iData = i;
    }

    public int getKey() {
        return this.iData;
    }

    public String getColor() {
        if (this.color == true) {
            return "B";
        } else {
            return "R";
        }
    }
}
