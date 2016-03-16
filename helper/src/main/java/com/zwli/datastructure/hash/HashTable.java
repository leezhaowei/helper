package com.zwli.datastructure.hash;

public class HashTable {

    private DataItem[] hashArray; // array holds hash table

    private int arraySize;

    private DataItem nonItem; // for deleted items

    public HashTable(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1); // deleted item key is -1
    }

    public void displayTable() {
        System.out.print("Table: [");
        for (int j = 0; j < arraySize; j++) {
            if (j != arraySize - 1) {
                if (hashArray[j] != null) {
                    System.out.print(hashArray[j].getKey() + ", ");
                } else {
                    System.out.print("**, ");
                }
            } else if (hashArray[j] != null) {
                System.out.print(hashArray[j].getKey());
            } else {
                System.out.print("**");
            }
        }
        System.out.println("]");
    }

    public int hashFunc(int key) {
        return key % arraySize; // hash function
    }

    public void insert(DataItem item) {
        int key = item.getKey(); // extract key
        int hashVal = hashFunc(key); // hash the key
        // until empty cell or -1,
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal = rehash(hashVal);
        }
        hashArray[hashVal] = item; // insert item
    } // end insert()

    public DataItem delete(int key) {
        int hashVal = hashFunc(key); // hash the key

        while (hashArray[hashVal] != null) { // until empty cell,
            // found the key?
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem; // delete item
                return temp; // return item
            }
            hashVal = rehash(hashVal);
        }
        return null; // can't find item
    } // end delete()

    public DataItem find(int key) {
        int hashVal = hashFunc(key); // hash the key

        while (hashArray[hashVal] != null) { // until empty cell,
            // found the key?
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal]; // yes, return item
            }
            hashVal = rehash(hashVal);
        }
        return null; // can't find item
    }

    private int rehash(int hashVal) {
        ++hashVal; // go to next cell
        hashVal %= arraySize; // wraparound if necessary
        return hashVal;
    }

}
