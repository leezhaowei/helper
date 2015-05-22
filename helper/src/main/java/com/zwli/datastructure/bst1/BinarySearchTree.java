package com.zwli.datastructure.bst1;

public class BinarySearchTree {

    private BSTNode root;

    public BinarySearchTree() {
        super();
        this.root = null;
    }

    public boolean add(int value) {
        if (root == null) {
            root = new BSTNode(value);
            return true;
        } else {
            return root.add(value);
        }
    }

    public boolean search(int value) {
        if (root == null) {
            return false;
        } else {
            return root.search(value);
        }
    }

    public boolean remove(int value) {
        if (root == null) {
            return false;
        } else {
            if (root.getValue() == value) {
                BSTNode auxRoot = new BSTNode(0);
                auxRoot.setLeft(root);
                boolean result = root.remove(value, auxRoot);
                root = auxRoot.getLeft();
                return result;
            } else {
                return root.remove(value, null);
            }
        }
    }
}
