package com.zwli.datastructure.linkedlist;

import java.util.Arrays;

public class SortedListImpl<E extends Comparable<? super E>> extends LinkListImpl<E> implements SortedList<E> {

    public SortedListImpl() {
        head = null;
    }

    public SortedListImpl(final E[] array) {
        head = null;
        for (E element : array) {
            insert(element);
        }
    }

    @Override
    public void insert(final E data) {
        Link<E> newLink = new Link<E>(data);
        Link<E> previous = null;
        Link<E> current = head;
        while (current != null && data.compareTo(current.data) > 0) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            head = newLink;
        } else {
            previous.next = newLink;
        }
        newLink.next = current;
    }

    @Override
    public Link<E> remove() {
        return deleteHead();
    }

    public static void main(final String[] args) {
        test();
        System.out.println("===========================================================");
        testInsertionSort();
    }

    public static void testInsertionSort() {
        int size = 10;
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            int data = (int) (Math.random() * 99);
            array[i] = Integer.valueOf(data);
        }
        System.out.print("Unsorted array: ");
        // for (int i = 0; i < size; i++) {
        // System.out.print(array[i] + " ");
        // }
        // System.out.println();
        System.out.println(Arrays.toString(array));

        SortedList<Integer> list = new SortedListImpl<Integer>(array);
        for (int i = 0; i < size; i++) {
            array[i] = list.remove().data;
        }
        System.out.print("Sorted array  : ");
        System.out.println(Arrays.toString(array));

    }

    public static void test() {
        SortedList<Integer> list = new SortedListImpl<Integer>();
        list.insert(22);
        list.insert(44);
        System.out.print("List (head --> tail): ");
        list.displayList();

        list.insert(11);
        list.insert(33);
        list.insert(55);
        System.out.print("List (head --> tail): ");
        list.displayList();

        list.remove();
        System.out.print("List (head --> tail): ");
        list.displayList();
    }

}
