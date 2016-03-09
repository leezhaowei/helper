package com.zwli.datastructure.binarytree;

public class BinaryTreeImpl<T extends Comparable<T>> implements BinaryTree<T> {

    public static enum PrintOrder {
        INORDER, PREORDER, POSTORDER;
    }

    private Node<T> root;

    @Override
    public void insert(final T data) {
        Node<T> newNode = new Node<T>(data);
        if (root == null) {
            root = newNode;
        } else {
            Node<T> current = root;
            Node<T> parent;
            while (true) {
                parent = current;
                if (data.compareTo(current.data) < 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void displayTree() {
        printInorder(root);
    }

    public void printPostorder(final Node<T> current) {
        if (current == null) {
            return;
        }
        printPostorder(current.left);
        printPostorder(current.right);
        System.out.print(String.valueOf(current.data) + " ");
    }

    public void printInorder(final Node<T> current) {
        if (current == null) {
            return;
        }
        printInorder(current.left);
        System.out.print(String.valueOf(current.data) + " ");
        printInorder(current.right);
    }

    public void printPreorder(final Node<T> current) {
        if (current == null) {
            return;
        }
        System.out.print(String.valueOf(current.data) + " ");
        printInorder(current.left);
        printInorder(current.right);
    }

    public void printTree(final PrintOrder order) {
        if (PrintOrder.INORDER == order) {
            printInorder(root);
        } else if (PrintOrder.PREORDER == order) {
            printPreorder(root);
        } else if (PrintOrder.POSTORDER == order) {
            printPostorder(root);
        }
    }

    @Override
    public Node<T> find(final T data) {
        Node<T> current = root;
        while (!current.data.equals(data)) {
            if (current.data.compareTo(data) < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    @Override
    public boolean delete(final T key) {
        // 先找到需要删除的节点
        Node<T> current = root;
        Node<T> parent = root;
        boolean isLeft = false;
        // 显然，当current.data == key 时，current 就是要找的节点
        while (current.data.equals(key)) {
            parent = current;
            if (key.compareTo(current.data) < 0) {
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

        // 分情况考虑删除的节点
        // 删除的节点为叶节点时
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) { // 删除的节点有一个子节点
            // 删除的节点只有一个左子节点时
            if (current == root) {
                root = current.left;
            } else if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right = current.left;// 要删除的节点是一个右子节点
            }
        } else if (current.left == null) {
            // 删除的节点只有一个右子节点时
            if (current == root) {
                root = current.right;
            } else if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;// 要删除的节点是一个右子节点
            }
        } else { // 删除的节点有两个子节点
            Node<T> successor = getSuccessor(current);// 找到后继节点
            if (current == root) {
                root = successor;
            } else if (isLeft) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    private Node<T> getSuccessor(final Node<T> delNode) {
        Node<T> successorParent = delNode; // 后继节点的父节点
        Node<T> successor = delNode; // 后继节点
        Node<T> current = delNode.right; // 移动到位置节点位置
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != delNode.right) {
            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }

    @Override
    public void inOrder(final Node<T> root) {
        if (root != null) {
            inOrder(root.left);
            root.displayNode();
            inOrder(root.right);
        }
    }

    @Override
    public Node<T> findMinNode() {
        Node<T> current, last;
        last = null;
        current = root;
        if (current.left == null) {
            return current;
        } else {
            while (current != null) {
                last = current;
                current = current.left;
            }
            return last;
        }
    }

    @Override
    public int size() {
        return calculateSize(root);
    }

    private int calculateSize(final Node<T> current) {
        if (current == null) {
            return 0;
        } else {
            return calculateSize(current.left) + 1 + calculateSize(current.right);
        }
    }

    @Override
    public int maxDepth(final Node<T> current) {
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

    public int depth(final Node<T> root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(depth(root.left), depth(root.right));
        }
    }

}
