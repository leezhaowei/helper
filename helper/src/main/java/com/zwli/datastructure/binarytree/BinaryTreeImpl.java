package com.zwli.datastructure.binarytree;

public class BinaryTreeImpl implements ITree {

    private TreeNode root;

    @Override
    public void insert(int data) {
        TreeNode newNode = new TreeNode(data);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (data < current.data) {
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
        // TODO Auto-generated method stub
    }

    @Override
    public TreeNode find(int data) {
        TreeNode current = root;
        while (current.data != data) {
            if (current.data < data) {
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
    public boolean delete(int key) {
        // 先找到需要删除的节点
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = false;

        while (current.data != key) { // 显然，当current.data == key 时，current 就是要找的节点
            parent = current;
            if (key < current.data) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
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
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) { // 删除的节点有一个子节点
            // 删除的节点只有一个左子节点时
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;// 要删除的节点是一个右子节点
            }
        } else if (current.left == null) {
            // 删除的节点只有一个右子节点时
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;// 要删除的节点是一个右子节点
            }
        } else { // 删除的节点有两个子节点
            TreeNode successor = getSuccessor(current);// 找到后继节点
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    private TreeNode getSuccessor(TreeNode delNode) {
        TreeNode successorParent = delNode; // 后继节点的父节点
        TreeNode successor = delNode; // 后继节点
        TreeNode current = delNode.right; // 移动到位置节点位置
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
    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            root.displayNode();
            inOrder(root.right);
        }
    }

    public TreeNode findMinNode() {
        TreeNode current, last;
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
}
