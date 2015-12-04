package com.zwli.datastructure.tree;

public class Tree {

    private static class Node {

        int data;
        Node left;
        Node right;

        public Node(int d) {
            data = d;
        }
    }

    static enum PrintOrder {
        INORDER, PREORDER, POSTORDER;
    }

    Node root;
    private int countOfSingleValuedSubTree = 0;

    public Tree() {
    }

    public Tree(Node n) {
        root = n;
    }

    private boolean countSingleRec(Node current) {
        if (current == null) { return true; }

        boolean left = countSingleRec(current.left);
        boolean right = countSingleRec(current.right);
        if (!left || !right) { return false; }
        if (current.left != null && current.data != current.left.data) { return false; }
        if (current.right != null && current.data != current.right.data) { return false; }

        countOfSingleValuedSubTree++;
        return true;
    }

    public int countSingle() {
        countSingleRec(root);
        return countOfSingleValuedSubTree;
    }

    public void printPostorder(Node current) {
        if (current == null) { return; }
        printPostorder(current.left);
        printPostorder(current.right);
        System.out.print(current.data + " ");
    }

    public void printInorder(Node current) {
        if (current == null) { return; }
        printInorder(current.left);
        System.out.print(current.data + " ");
        printInorder(current.right);
    }

    public void printPreorder(Node current) {
        if (current == null) { return; }
        System.out.print(current.data + " ");
        printInorder(current.left);
        printInorder(current.right);
    }

    public void printTree(PrintOrder order) {
        if (PrintOrder.INORDER == order) {
            printInorder(root);
        } else if (PrintOrder.PREORDER == order) {
            printPreorder(root);
        } else if (PrintOrder.POSTORDER == order) {
            printPostorder(root);
        }
    }

    public int size() {
        return calculateSize(root);
    }

    private int calculateSize(Node current) {
        if (current == null) {
            return 0;
        } else {
            return calculateSize(current.left) + 1 + calculateSize(current.right);
        }
    }

    public boolean identicalTrees(Node other) {
        return validateTrees(root, other);
    }

    private boolean validateTrees(Node current, Node other) {
        if (current == null && other == null) { return true; }
        if (current != null && other != null) {
            boolean a = current.data == other.data;
            boolean b = validateTrees(current.left, other.left);
            boolean c = validateTrees(current.right, other.right);
            if (a && b && c) { return true; }
        }
        return false;
    }

    public int maxDepth(Node current) {
        if (current == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(current.left);
            int rightDepth = maxDepth(current.right);
            if (leftDepth > rightDepth) {
                return leftDepth + 1;
            } else {
                return rightDepth + 1;
            }
        }
    }

    public void mirror(Node current) {
        if (current == null) {
            return;
        } else {
            mirror(current.left);
            mirror(current.right);

            Node tmp = null;
            tmp = current.left;
            current.left = current.right;
            current.right = tmp;
        }
    }

    public void printPaths(Node current) {
        int path[] = new int[10];
        printPathsRecur(current, path, 0);
    }

    private void printPathsRecur(Node current, int[] path, int pathLen) {
        if (current == null) { return; }
        path[pathLen] = current.data;
        pathLen++;
        if (current.left == null && current.right == null) {
            printArray(path, pathLen);
        } else {
            printPathsRecur(current.left, path, pathLen);
            printPathsRecur(current.right, path, pathLen);
        }
    }

    private void printArray(int path[], int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();
    }

    public void printLevelOrder(Node current) {

    }

    public static void main(String[] args) {
        // testCountSingleRec();
        // testPrint();
        // testSize();
        // testIdenticalTrees();
        // testMaxDepth();
        // testMirror();
        testPrintPaths();
    }

    static void testPrintPaths() {
        Node n = initTree();
        Tree t = new Tree();
        t.printPaths(n);
    }

    static void testMirror() {
        Node n = initTree();
        Tree t = new Tree();
        System.out.println("Inorder traversal of the constructed tree is ");
        t.printInorder(n);
        t.mirror(n);
        System.out.println("\nInorder traversal of the mirror tree is ");
        t.printInorder(n);
    }

    static void testMaxDepth() {
        Node n = initTree();
        Tree t = new Tree();
        System.out.println("Hight of tree is " + t.maxDepth(n));
    }

    private static Node initTree() {
        Node n = initNode(1);
        n.left = initNode(2);
        n.right = initNode(3);
        n.left.left = initNode(4);
        n.left.right = initNode(5);
        return n;
    }

    static void testIdenticalTrees() {
        Node a = initTree();
        Node b = initTree();

        Tree t = new Tree(a);
        System.out.println(t.identicalTrees(b));
    }

    static void testSize() {
        Node root = initTree();
        Tree t = new Tree(root);
        System.out.println("Size of the tree is " + t.size());
    }

    static void testPrint() {
        Node root = initTree();

        Tree t = new Tree(root);
        System.out.println("\nPreorder traversal of binary tree is");
        t.printTree(PrintOrder.PREORDER);
        System.out.println("\nInorder traversal of binary tree is");
        t.printTree(PrintOrder.INORDER);
        System.out.println("\nPostorder traversal of binary tree is");
        t.printTree(PrintOrder.POSTORDER);
    }

    static void testCountSingleRec() {
        Node root = initNode(5);
        root.left = initNode(4);
        root.right = initNode(5);
        root.left.left = initNode(4);
        root.left.right = initNode(4);
        root.right.right = initNode(5);
        Tree tree = new Tree(root);
        int count = tree.countSingle();
        System.out.println(count);
    }

    static Node initNode(int data) {
        return new Node(data);
    }
}
