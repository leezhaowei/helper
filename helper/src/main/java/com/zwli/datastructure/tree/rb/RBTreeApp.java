package com.zwli.datastructure.tree.rb;

import java.util.Random;

public class RBTreeApp {

    public static void main(String[] args) {
        RBTree rbtree = new RBTree();
        int[] a = { 13, 8, 11, 17, 15, 6, 1, 22, 25, 27 };
        for (int element : a) {
            rbtree.insertRB(element);
        }
        rbtree.preOrder();

        rbtree.deleteRB(27);

        System.out.println();

        rbtree.preOrder();

        rbtree.deleteRB(25);
        System.out.println();

        rbtree.preOrder();

        System.out.println();

        // compute the data from 1 to 300000;
        RandomNum rand = new RandomNum();
        System.out.println("Please waiting......., it builting the Array.");
        rand.setRandom();
        System.out.println("Please waiting......., it builting the tree.");
        RBTree randTree = new RBTree();
        for (int i = 0; i < 300000; i++) {
            randTree.insertRB(rand.A[i]);
        }

        long t1 = System.currentTimeMillis();
        Node node = randTree.search(123456);
        t1 = System.currentTimeMillis() - t1;
        if (node.iData == 123456) {
            System.out.println("You got the node with key = 123456,it cost " + t1 + "ms.");
        } else {
            System.out.println("You did not get the node with key = 123456");
        }

    }

    static class RandomNum {

        int[] A;

        // develop a array random number of double type
        void setRandom() {
            int j = 0;
            double k;
            A = new int[300000];
            Random rand = new Random();

            for (int i = 1; i <= 300000; i++) {
                if (i == 300000) {
                    System.out.println(i);
                }
                do {
                    k = rand.nextDouble();
                    j = (int) ((k * 1000000000) % 300000);
                } while ((j == 0) || (j == 299999) || ((A[j] != 0) && (A[j - 1] != 0) && (A[j + 1] != 0)));
                if (A[j] == 0) {
                    A[j] = i;
                } else if (A[j - 1] == 0) {
                    A[j - 1] = i;
                } else if (A[j + 1] == 0) {
                    A[j + 1] = i;
                }
            }
        }
    }
}
