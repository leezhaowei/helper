package com.zwli.datastructure.array;

public class LowArrayApp {

    public static void main(String[] args) {
        LowArray arr; // reference
        arr = new LowArray(100); // create LowArray object
        int nElems = 0; // number of items in array
        int j; // loop variable
        arr.setElem(0, 77); // insert 10 items
        arr.setElem(1, 99);
        arr.setElem(2, 44);
        arr.setElem(3, 55);
        arr.setElem(4, 22);
        arr.setElem(5, 88);
        arr.setElem(6, 11);
        arr.setElem(7, 00);
        arr.setElem(8, 66);
        arr.setElem(9, 33);
        nElems = 10; // now 10 items in array
        for (j = 0; j < nElems; j++) {
            System.out.print(arr.getElem(j) + " ");
        }
        System.out.println("");
        int searchKey = 26; // search for data item
        for (j = 0; j < nElems; j++) {
            if (arr.getElem(j) == searchKey) {
                break;
            }
        }
        if (j == nElems) {
            System.out.println("Can’t find " + searchKey);
        } else {
            System.out.println("Found " + searchKey);
        }
        // delete value 55
        for (j = 0; j < nElems; j++) {
            if (arr.getElem(j) == 55) {
                break;
            }
        }
        for (int k = j; k < nElems; k++) {
            arr.setElem(k, arr.getElem(k + 1));
        }
        nElems--; // decrement size
        for (j = 0; j < nElems; j++) {
            System.out.print(arr.getElem(j) + " ");
        }
        System.out.println("");
    }
}
