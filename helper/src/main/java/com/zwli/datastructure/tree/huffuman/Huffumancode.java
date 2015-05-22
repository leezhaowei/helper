package com.zwli.datastructure.tree.huffuman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Huffumancode {

    static float sum = 0;

    static String s;

    public static void main(String[] args) {
        new Huffumancode().run();
    }

    public void run() {
        try {

            FileReader fr = new FileReader("WordAndFreq.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            Node[] A = new Node[24686];
            A[0] = new Node();
            A[0].freq = 24685;
            int i = 1;
            String[] st = new String[2];
            while ((s = br.readLine()) != null) {
                st = s.split("\\s");
                A[i] = new Node();
                A[i].data = st[0];
                A[i].freq = Integer.parseInt(st[1]);
                i++;
            }
            System.out.println(A[24685].data + A[24685].freq);
            heap_sort(A);
            A[0].freq = 24685;
            build_huffmantree(A);

            System.out.println("A[1]ede pin ldu" + A[1].freq);

            FileWriter fw = new FileWriter("output.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            preorder(A[1], bw);
            bw.flush();

            float average = sum / 3330999;
            String s1 = "the average length of the code is :".concat(Float.toString(average));
            bw.write(s1);
            bw.close();
            br.close();
            System.out.println(average);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void min_Heapify(Node[] A, int i) {
        int l = 2 * i;
        int r = 2 * i + 1;
        int small;
        if (l <= A[0].freq && A[l].freq < A[i].freq) {
            small = l;
        } else {
            small = i;
        }
        if (r <= A[0].freq && A[r].freq < A[small].freq) {
            small = r;
        }
        // System.out.println("haha ");
        if (small != i) {
            Node node1 = new Node();
            node1 = A[small];
            A[small] = A[i];
            A[i] = node1;
            min_Heapify(A, small);

            // depth_visited(A[1]);
        }
    }

    public void build_min_heap(Node[] B) {
        int len = B[0].freq;
        for (int j = len / 2; j > 0; j--) {
            min_Heapify(B, j);
        }
    }

    public void heap_sort(Node[] b) {
        build_min_heap(b);
        int k = b[0].freq;
        for (int i = k; i >= 2; i--) {
            Node node1 = new Node();
            node1 = b[1];
            b[1] = b[i];
            b[i] = node1;
            b[0].freq = b[0].freq - 1;
            min_Heapify(b, 1);

        }

    }

    public void build_huffmantree(Node[] B) {
        int len = B[0].freq;
        Node node;
        while (len > 1) {
            // System.out.println("len ������:"+len);
            // System.out.println("B[len] ��Ƶ��"+B[len].freq);
            // System.out.println("B[len-1] ��Ƶ��"+B[len-1].freq);
            node = new Node();
            node.freq = B[len].freq + B[len - 1].freq;

            node.left = B[len];
            node.right = B[len - 1];
            B[len] = null;
            B[len - 1] = null;

            // System.out.println("node ��Ƶ�ʣ�"+node.freq);

            int k = len - 2;
            while (k >= 1) {
                if (B[k].freq < node.freq) {
                    B[k + 1] = B[k];
                } else {
                    break;
                }
                k--;

            }

            B[k + 1] = node;
            // System.out.println(B[k+1].freq);
            // System.out.println(B[9].freq) ;
            len--;
            node = null;
            // sort(B,len);

            // System.out.println(index--);

        }
    }

    public void preorder(Node node, BufferedWriter bt) {
        if (node != null) {

            if (node.left != null && node.right != null) {
                if (node.code != null) {
                    node.left.code = "0".concat(node.code);
                } else {
                    node.left.code = "0";
                }
                if (node.code != null) {
                    node.right.code = "1".concat(node.code);
                } else {
                    node.right.code = "1";
                }
            }
            if (node.left == null && node.right == null) {
                try {
                    s = node.data.concat(" ");
                    s = s.concat(node.code);
                    bt.write(s);
                    bt.newLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
                // System.out.println(node.data+ " "+node.code);
                sum = sum + node.code.length() * node.freq;
            }

            preorder(node.left, bt);
            preorder(node.right, bt);
        }
    }

}
