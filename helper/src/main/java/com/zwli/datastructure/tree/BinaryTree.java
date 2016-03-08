package com.zwli.datastructure.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * binary tree(二叉树)简单实现
 */
public class BinaryTree<T> {

    private Node<T> root;

    public BinaryTree() {
        root = null;
    }

    public Node<T> find(final int key) {
        Node<T> current = root;
        while (current.key != key) {
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public void insert(final int key, final T data) {
        Node<T> newNode = new Node<T>();
        newNode.key = key;
        newNode.data = data;
        if (root == null) {
            root = newNode;
        } else {
            Node<T> current = root;
            Node<T> parent;
            while (true) {
                parent = current;
                if (key < current.key) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) { // insert on right
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(final int key) {
        Node<T> current = root;
        Node<T> parent = root;
        boolean isLeft = true;

        while (current.key != key) {
            parent = current;
            if (key < current.key) {
                isLeft = true;
                current = current.left;
            } else {
                isLeft = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }

        if (current.left == null && current.right == null) {// 要删除的节点没有左右子节点
            if (current == root) {
                root = null; // 树被清空
            } else if (isLeft) {
                parent.left = null; // 要删除的节点是左节点
            } else {
                parent.right = null; // 要删除的节点是右节点
            }
        } else if (current.right == null) { // 要删除的节点没有右节点
            if (current == root) {
                root = current.left;
            } else if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) { // 要删除的节点没有左节点
            if (current == root) {
                root = current.right;
            } else if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else { // 要删除的节点有左右子节点
            Node<T> successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeft) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }

            // 后继节点代替要删除的节点的位置，要继承删除节点的子节点
            successor.left = current.left;
        }
        return true;
    }

    /*
     * 查询中序后继节点，用于代替被删除的节点
     */
    private Node<T> getSuccessor(final Node<T> delNode) {
        Node<T> successorParent = delNode; // 后继节点父节点
        Node<T> successor = delNode; // 后继节点
        Node<T> current = delNode.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        // 后继节点肯定没有左子节点
        if (successor != delNode.right) {
            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }

    public void traverse(final int traverseType) {
        switch (traverseType) {
        case 1:
            System.out.print("\nPreorder traversal: ");
            preOrder(root);
            break;
        case 2:
            System.out.print("\nInorder traversal:  ");
            inOrder(root);
            break;
        case 3:
            System.out.print("\nPostorder traversal: ");
            postOrder(root);
            break;
        }
        System.out.println();
    }

    private void preOrder(final Node<T> localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.key + " ");
            preOrder(localRoot.left);
            preOrder(localRoot.right);
        }
    }

    private void inOrder(final Node<T> localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.left);
            System.out.print(localRoot.key + " ");
            inOrder(localRoot.right);
        }
    }

    private void postOrder(final Node<T> localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.left);
            postOrder(localRoot.right);
            System.out.print(localRoot.key + " ");
        }
    }

    public void displayTree() {
        Stack<Node<T>> globalStack = new Stack<Node<T>>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");
        while (isRowEmpty == false) {
            Stack<Node<T>> localStack = new Stack<Node<T>>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++) {
                System.out.print(' ');
            }

            while (globalStack.isEmpty() == false) {
                Node<T> temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.key);
                    localStack.push(temp.left);
                    localStack.push(temp.right);

                    if (temp.left != null || temp.right != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(' ');
                }
            }
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println("......................................................");
    }

    public static void main(final String[] args) throws IOException {
        int value;
        BinaryTree<Double> theTree = new BinaryTree<Double>();

        theTree.insert(50, 1.5);
        theTree.insert(25, 1.2);
        theTree.insert(75, 1.7);
        theTree.insert(12, 1.5);
        theTree.insert(37, 1.2);
        theTree.insert(43, 1.7);
        theTree.insert(30, 1.5);
        theTree.insert(33, 1.2);
        theTree.insert(87, 1.7);
        theTree.insert(93, 1.5);
        theTree.insert(97, 1.5);
        theTree.insert(49, 1.5);
        theTree.insert(51, 1.5);

        while (true) {
            System.out.print("Enter first letter of show, ");
            System.out.print("insert, find, delete, or traverse: ");
            int choice = getChar();
            switch (choice) {
            case 's':
                theTree.displayTree();
                break;
            case 'i':
                System.out.print("Enter value to insert: ");
                value = getInt();
                theTree.insert(value, value + 0.9);
                break;
            case 'f':
                System.out.print("Enter value to find: ");
                value = getInt();
                Node<Double> found = theTree.find(value);
                if (found != null) {
                    System.out.print("Found: ");
                    found.displayNode();
                    System.out.print("\n");
                } else {
                    System.out.print("Could not find ");
                }
                System.out.print(value + '\n');
                break;
            case 'd':
                System.out.print("Enter value to delete: ");
                value = getInt();
                boolean didDelete = theTree.delete(value);
                if (didDelete) {
                    System.out.print("Deleted " + value + '\n');
                } else {
                    System.out.print("Could not delete ");
                }
                System.out.print(value + '\n');
                break;
            case 't':
                System.out.print("Enter type 1, 2 or 3: ");
                value = getInt();
                theTree.traverse(value);
                break;
            default:
                System.out.print("Invalid entry\n");
            } // end switch
        } // end while
    } // end main()

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
