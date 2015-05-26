package com.zwli.datastructure.array;

public class HighArray {

    private long[] a;

    private int nElems;

    public HighArray(int max) {
        a = new long[max]; // create the array
        nElems = 0; // no items yet
    }

    public boolean find(long searchKey) {
        int j;
        for (j = 0; j < nElems; j++) {
            // for each element,
            if (a[j] == searchKey) {
                break; // exit loop before end
            }
        }
        if (j == nElems) {
            return false; // yes, can’t find it
        } else {
            return true; // no, found it
        }
    }

    public int findByBinary(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;
        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] == searchKey) {
                return curIn; // found it
            } else if (lowerBound > upperBound) {
                return nElems; // can’t find it
            } else { // divide range
                if (a[curIn] < searchKey) {
                    lowerBound = curIn + 1; // it’s in upper half
                } else {
                    upperBound = curIn - 1; // it’s in lower half
                }
            }
        }
    }

    public void insert(long value) {
        a[nElems] = value; // insert it
        nElems++; // increment size
    }

    public boolean delete(long value) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (value == a[j]) {
                break;
            }
        }
        if (j == nElems) {
            return false;
        } else { // found it
            for (int k = j; k < nElems; k++) {
                // move higher ones down
                a[k] = a[k + 1];
            }
            nElems--; // decrement size
            return true;
        }
    }

    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("");
    }
}
