package com.zwli.datastructure.bst1;

/**
 * Binary search tree<br>
 * tree node<br>
 * Created by zwli on Apr 30, 2015
 */
public class BSTNode {

    private int value;

    private BSTNode left;

    private BSTNode right;

    public BSTNode(int value) {
        this.value = value;
        left = null;
        right = null;
    }

    public boolean add(int value) {
        if (value == this.value) {
            return false;
        } else if (value < this.value) {
            if (left == null) {
                left = new BSTNode(value);
                return true;
            } else {
                return left.add(value);
            }
        } else if (value > this.value) {
            if (right == null) {
                right = new BSTNode(value);
                return true;
            } else {
                return right.add(value);
            }
        }
        return false;
    }

    public boolean search(int value) {
        if (value == this.value) {
            return true;
        } else if (value < this.value) {
            if (left == null) {
                return false;
            } else {
                return left.search(value);
            }
        } else if (value > this.value) {
            if (right == null) {
                return false;
            } else {
                return right.search(value);
            }
        }
        return false;
    }

    public boolean remove(int value, BSTNode parent) {
        if (value < this.value) {
            if (left != null) {
                return left.remove(value, this);
            } else {
                return false;
            }
        } else if (value > this.value) {
            if (right != null) {
                return right.remove(value, this);
            } else {
                return false;
            }
        } else {
            if (left != null && right != null) {
                this.value = right.minValue();
                right.remove(this.value, this);
            } else if (parent.left == this) {
                parent.left = (left != null) ? left : right;
            } else if (parent.right == this) {
                parent.right = (left != null) ? left : right;
            }
            return true;
        }
    }

    public int minValue() {
        if (left == null) {
            return value;
        } else {
            return left.minValue();
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
}
