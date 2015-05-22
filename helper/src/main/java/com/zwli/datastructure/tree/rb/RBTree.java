package com.zwli.datastructure.tree.rb;

public class RBTree {

    private Node root;

    private static Node nil = new Node();

    public RBTree() {
        initNil();
    }

    private void initNil() {
        nil.iData = 0;
        nil.color = true;
        nil.leftChild = null;
        nil.rightChild = null;
    }

    private void setRoot(Node node) {
        this.root = node;
    }

    private void preOrder(Node root) {
        if (root != null) {
            System.out.print("(" + root.iData + root.getColor() + ",");
        }
        if (root.leftChild == nil) {
            System.out.print("NIL,");
        } else {
            preOrder(root.leftChild);
            System.out.print(",");
        }
        if (root.rightChild == nil) {
            System.out.print("NIL)");
        } else {
            preOrder(root.rightChild);
            System.out.print(")");
        }
    }

    private void insert(Node newNode) {
        if (root == null) { // no node in root
            root = newNode;
            root.parent = nil;
        } else // root occupied
        {
            Node current = root; // start at root
            Node parent;
            while (true) { // (exits internally)
                parent = current;
                if (newNode.iData < current.iData) // go left
                {
                    current = current.leftChild;
                    if (current == nil) { // if end of the line,
                        // insert on left
                        parent.leftChild = newNode;
                        newNode.parent = parent;
                        return;
                    }
                } // end if go left
                else { // or go right
                    current = current.rightChild;
                    if (current == nil) { // if end of the line
                        // insert on right
                        parent.rightChild = newNode;
                        newNode.parent = parent;
                        return;
                    }
                } // end else go right
            } // end while
        } // end else not root
    }

    private void leftRotate(Node node) {
        Node y;
        y = node.rightChild;

        node.rightChild = y.leftChild;
        if (y.leftChild != nil) {
            y.leftChild.parent = node;
        }

        y.parent = node.parent;
        if (node.parent == nil) {
            this.setRoot(y);
        } else if (node == node.parent.leftChild) {
            node.parent.leftChild = y;
        } else {
            node.parent.rightChild = y;
        }
        y.leftChild = node;
        node.parent = y;
    }

    private void rightRotate(Node node) {
        Node y;
        y = node.leftChild;

        node.leftChild = y.rightChild;
        if (y.rightChild != nil) {
            y.rightChild.parent = node;
        }

        y.parent = node.parent;
        if (node.parent == nil) {
            this.setRoot(y);
        } else if (node == node.parent.leftChild) {
            node.parent.leftChild = y;
        } else {
            node.parent.rightChild = y;
        }
        y.rightChild = node;
        node.parent = y;
    }

    private void rbInsertAdjust(Node node) {
        while (node.parent.color == false) {
            if (node.parent == node.parent.parent.leftChild) {
                Node y = node.parent.parent.rightChild;
                if (y.color == false) {
                    node.parent.color = true;
                    y.color = true;
                    node.parent.parent.color = false;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.rightChild) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = true;
                    node.parent.parent.color = false;
                    rightRotate(node.parent.parent);

                }
            } else {
                Node y = node.parent.parent.leftChild;
                if (y.color == false) {
                    node.parent.color = true;
                    y.color = true;
                    node.parent.parent.color = false;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.leftChild) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = true;
                    node.parent.parent.color = false;
                    leftRotate(node.parent.parent);
                }
            }
        }
        this.getRoot().color = true;
    }

    private Node getMin(Node node) {
        while (node.leftChild != nil) {
            node = node.leftChild;
        }
        return node;
    }

    private Node getSuccessor(Node node) {
        if (node.rightChild != nil) {
            return getMin(node.rightChild);
        }
        Node y = node.parent;
        while ((y != nil) && (y.rightChild == node)) {
            node = y;
            y = y.parent;
        }
        return y;
    }

    private void rbDeleteAdjust(Node node) {
        while (node != getRoot() && node.color == true) {
            if (node == node.parent.leftChild) {
                Node y = node.parent.rightChild;
                if (y.color == false) {
                    y.color = true;
                    node.parent.color = false;
                    leftRotate(node.parent);
                    y = node.parent.rightChild;
                }
                if ((y.leftChild.color == true) && (y.rightChild.color == true)) {
                    y.color = false;
                    node = node.parent;
                } else if (y.rightChild.color == true) {
                    y.leftChild.color = true;
                    y.color = false;
                    rightRotate(y);
                    y = node.parent.rightChild;
                }
                y.color = node.parent.color;
                node.parent.color = true;
                y.rightChild.color = true;
                leftRotate(node.parent);
                node = getRoot();
            } else {
                Node y = node.parent.leftChild;
                if (y.color == false) {
                    y.color = true;
                    node.parent.color = false;
                    rightRotate(node.parent);
                    y = node.parent.leftChild;
                }
                if ((y.rightChild.color == true) && (y.leftChild.color == true)) {
                    y.color = false;
                    node = node.parent;
                } else if (y.leftChild.color == true) {
                    y.rightChild.color = true;
                    y.color = false;
                    leftRotate(y);
                    y = node.parent.leftChild;
                }
                y.color = node.parent.color;
                node.parent.color = true;
                y.leftChild.color = true;
                rightRotate(node.parent);
                node = getRoot();
            }
        }
        node.color = true;
    }

    private Node rbDelete(Node node) {
        Node y, x;
        if ((node.leftChild == nil) || (node.rightChild == nil)) {
            y = node;
        } else {
            y = getSuccessor(node);
        }
        if (y.leftChild != nil) {
            x = y.leftChild;
        } else {
            x = y.rightChild;
        }
        x.parent = y.parent;
        if (y.parent == nil) {
            setRoot(x);
        } else if (y == y.parent.leftChild) {
            y.parent.leftChild = x;
        } else {
            y.parent.rightChild = x;
        }
        if (y != node) {
            node.iData = y.iData;
        }
        if (y.color == true) {
            rbDeleteAdjust(x);
        }
        return y;
    }

    public Node getRoot() {
        return this.root;
    }

    // preOrder print the RBtree
    public void preOrder() {
        preOrder(this.root);
    }

    public void insertRB(int i) {
        // initNil();
        Node node = new Node();
        node.iData = i;
        insert(node);
        node.color = false;
        node.leftChild = nil;
        node.rightChild = nil;
        rbInsertAdjust(node);
    }

    public Node deleteRB(int i) {
        Node node;
        node = search(i);
        if (node == null) {
            return null;
        } else {
            return (rbDelete(node));
        }
    }

    public Node search(int key) {
        if (root == null) {
            return null;
        } else {
            Node current = root; // start at root
            while (current.iData != key) { // while no match,
                if (key < current.iData) {
                    current = current.leftChild;
                } else {
                    current = current.rightChild; // or go right
                }
                if (current == nil) {
                    return null; // didn't find it
                }
            }
            return current;
        }
    }
}
