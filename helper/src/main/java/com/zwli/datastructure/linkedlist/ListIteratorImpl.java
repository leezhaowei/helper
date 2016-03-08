package com.zwli.datastructure.linkedlist;

import java.util.Scanner;

public class ListIteratorImpl<E> implements ListIterator<E> {

    private Link<E> current;
    private Link<E> previous;
    private final LinkList<E> list;

    public ListIteratorImpl(final LinkList<E> list) {
        this.list = list;
        reset();
    }

    @Override
    public void reset() {
        current = list.getHead();
        previous = null;
    }

    @Override
    public boolean atEnd() {
        return current.next == null;
    }

    @Override
    public void nextLink() {
        previous = current;
        current = current.next;
    }

    @Override
    public Link<E> getCurrent() {
        return current;
    }

    @Override
    public void insertAfter(final E data) {
        Link<E> newLink = new Link<E>(data);
        if (list.isEmpty()) {
            list.setHead(newLink);
            current = newLink;
        } else {
            newLink.next = current.next;
            current.next = newLink;
            nextLink();
        }
    }

    @Override
    public void insertBefore(final E data) {
        Link<E> newLink = new Link<E>(data);
        if (previous == null) {
            newLink.next = list.getHead();
            list.setHead(newLink);
            reset();
        } else {
            newLink.next = previous.next;
            previous.next = newLink;
            current = newLink;
        }
    }

    @Override
    public E deleteCurrent() {
        E data = current.data;
        if (previous == null && list.isEmpty()) {
            list.setHead(current);
            reset();
        } else {
            if (previous != null) {
                previous.next = current.next;
            } else {
                list.setHead(current.next);
            }
            if (atEnd()) {
                reset();
            } else {
                current = current.next;
            }
        }
        return data;
    }

    public static void main(final String[] args) {
        // test1();
        test();
    }

    public static void test1() {
        LinkList<Integer> list = new LinkListImpl<Integer>();
        ListIterator<Integer> it = list.iterator();
        it.insertAfter(2);
        it.insertBefore(9);
        it.insertAfter(4);
        it.insertAfter(8);
        it.insertBefore(6);

        list.displayList();
        it.reset();

        Link<Integer> link = it.getCurrent();
        if (link.data % 3 == 0) {
            it.deleteCurrent();
        }
        while (!it.atEnd()) {
            it.nextLink();
            link = it.getCurrent();
            if (link.data % 3 == 0) {
                it.deleteCurrent();
            }
        }
        list.displayList();
    }

    public static void test() {
        LinkList<Integer> list = new LinkListImpl<Integer>();
        ListIterator<Integer> it = list.iterator();
        it.insertAfter(2);
        it.insertAfter(4);
        it.insertAfter(8);
        it.insertBefore(6);
        Scanner scanner = null;
        Integer data;
        try {
            scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter first letter of show, reset, next, get, before, after, delete: ");
                String s = scanner.next();
                int choice = s.charAt(0);
                switch (choice) {
                case 's':
                    if (!list.isEmpty()) {
                        System.out.print("List (head --> tail): ");
                        list.displayList();
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case 'r':
                    it.reset();
                    break;
                case 'n':
                    if (!list.isEmpty() && !it.atEnd()) {
                        it.nextLink();
                    } else {
                        System.out.println("Can't go to next link");
                    }
                    break;
                case 'g':
                    if (!list.isEmpty()) {
                        data = it.getCurrent().data;
                        System.out.println("Returned " + data);
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case 'b':
                    System.out.print("Enter value to insert: ");
                    data = scanner.nextInt();
                    it.insertBefore(data);
                    break;
                case 'a':
                    System.out.print("Enter value to insert: ");
                    data = scanner.nextInt();
                    it.insertAfter(data);
                    break;
                case 'd':
                    if (!list.isEmpty()) {
                        data = it.deleteCurrent();
                        System.out.println("Deleted " + data);
                    } else {
                        System.out.println("Can't delete");
                    }
                    break;
                case 'q':
                    return;
                default:
                    System.out.println("Invalid entry");
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
