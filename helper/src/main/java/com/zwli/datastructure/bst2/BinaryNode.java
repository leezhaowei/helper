package com.zwli.datastructure.bst2;

@SuppressWarnings("rawtypes")
public class BinaryNode {

    Comparable element; // The data in the node

    BinaryNode left; // Left child

    BinaryNode right; // Right child

    BinaryNode(Comparable theElement) {
        element = theElement;
        left = right = null;
    }
}
