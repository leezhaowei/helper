package com.zwli.datastructure.hash.chain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashChainApp {

    public static void main(String[] args) throws Exception {
        // test();
        a();
    }

    static void a() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(s + " = " + s.hashCode());
        for (int i = 0; i < 26; i++) {
            System.out.print((char) ('a' + i));
        }
    }

    static void test() throws IOException {
        int aKey;
        Link aDataItem;
        int size, n, keysPerCell = 100;
        // get sizes
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        // make table
        HashTable theHashTable = new HashTable(size);

        for (int j = 0; j < n; j++) {
            aKey = (int) (java.lang.Math.random() * keysPerCell * size);
            aDataItem = new Link(aKey);
            theHashTable.insert(aDataItem);
        }
        theHashTable.displayTable();
        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = getChar();
            switch (choice) {
            case 's':
                theHashTable.displayTable();
                break;
            case 'i':
                System.out.print("Enter key value to insert: ");
                aKey = getInt();
                aDataItem = new Link(aKey);
                theHashTable.insert(aDataItem);
                theHashTable.displayTable();
                break;
            case 'd':
                System.out.print("Enter key value to delete: ");
                aKey = getInt();
                theHashTable.delete(aKey);
                theHashTable.displayTable();
                break;
            case 'f':
                System.out.print("Enter key value to find: ");
                aKey = getInt();
                aDataItem = theHashTable.find(aKey);
                if (aDataItem != null) {
                    System.out.println("Found " + aKey);
                } else {
                    System.out.println("Could not find " + aKey);
                }
                break;
            case 'e':
                System.out.println("Exit...");
                return;
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
