package com.zwli.datastructure.hash.chain;

class Link { // (could be other items)

    private int iData; // data item

    public Link next; // next link in list

    public Link(int it) {
        iData = it;
    }

    public int getKey() {
        return iData;
    }

    public void displayLink() {
        System.out.print(iData + ", ");
    }
}
