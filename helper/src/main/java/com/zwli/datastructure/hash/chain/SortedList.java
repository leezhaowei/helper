package com.zwli.datastructure.hash.chain;

public class SortedList {

    private Link first; // ref to first list item

    private int size;

    public SortedList() {
        first = null;
        size = 0;
    }

    public void insert(Link theLink) {
        int key = theLink.getKey();
        Link previous = null; // start at first
        Link current = first;
        // until end of list,
        while (current != null && key > current.getKey()) { // or current > key,
            previous = current;
            current = current.next; // go to next item
        }
        if (previous == null) {
            first = theLink; // first --> new link
        } else {
            // not at beginning,
            previous.next = theLink; // prev --> new link
        }
        theLink.next = current; // new link --> current
        size++;
    } // end insert()

    public void delete(int key) {
        Link previous = null; // start at first
        Link current = first;
        // until end of list,
        while (current != null && key != current.getKey()) { // or key == current,
            previous = current;
            current = current.next; // go to next link
        }
        // disconnect link
        if (previous == null && null != first) {// if beginning of list
            first = first.next; // delete first link
            size--;
        } else if (null != current) {
            // not at beginning
            previous.next = current.next; // delete current link
            size--;
        }
    } // end delete()

    public Link find(int key) {
        Link current = first; // start at first
        // until end of list,
        while (current != null && current.getKey() <= key) { // or key too small,
            if (current.getKey() == key) {
                return current; // found it, return link
            }
            current = current.next; // go to next item
        }
        return null; // didn't find it
    } // end find()

    public void displayList() {
        System.out.print("List (first-->last): ");
        Link current = first; // start at beginning of list
        int len = 0;
        while (current != null) { // until end of list,
            len++;
            // current.displayLink(); // print data
            if (len < size) {
                System.out.print(current.getKey() + ", ");
            } else {
                System.out.print(current.getKey() + ";");
            }
            current = current.next; // move to next link
        }
        System.out.print("\t[size=" + size + "]");
        System.out.println();
    }
}
