package com.zwli.datastructure.hash.chain;

public class HashTable {

    private SortedList[] hashArray; // array of lists

    private int arraySize;

    public HashTable(int size) {
        arraySize = size;
        hashArray = new SortedList[arraySize];
        for (int j = 0; j < arraySize; j++) {
            // fill array
            hashArray[j] = new SortedList(); // with lists
        }
    }

    public void displayTable() {
        for (int j = 0; j < arraySize; j++) {
            System.out.print(j + ". "); // display cell number
            hashArray[j].displayList(); // display list
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void insert(Link theLink) {
        int key = theLink.getKey();
        int hashVal = hashFunc(key); // hash the key
        hashArray[hashVal].insert(theLink); // insert at hashVal
    } // end insert()

    public void delete(int key) {
        int hashVal = hashFunc(key); // hash the key
        hashArray[hashVal].delete(key); // delete link
    } // end delete()

    public Link find(int key) {
        int hashVal = hashFunc(key); // hash the key
        Link theLink = hashArray[hashVal].find(key); // get link
        return theLink; // return link
    }

}
