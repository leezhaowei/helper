package com.zwli.datastructure.linkedlist;

public class LinkListImpl<E> implements LinkList<E> {

    protected Link<E> head;

    public LinkListImpl() {
        head = null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void displayList() {
        // System.out.println("List (head --> tail): ");
        Link<E> current = head;
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public void insertHead(final E data) {
        Link<E> newLink = new Link<E>(data);
        newLink.next = head;
        head = newLink;
    }

    @Override
    public Link<E> deleteHead() {
        Link<E> tmp = head;
        head = head.next;
        return tmp;
    }

    @Override
    public Link<E> find(final E key) {
        Link<E> current = head;
        while (!current.data.equals(key)) {
            if (current.next == null) {
                return null;
            } else {
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public Link<E> delete(final E key) {
        if (head == null) {
            return null;
        }

        Link<E> current = head;
        Link<E> previous = head;
        while (current.data != key) {
            if (current.next == null) {
                return null;
            } else {
                previous = current;
                current = current.next;
            }
        }
        if (current == head) {
            head = head.next;
        } else {
            previous.next = current.next;
        }
        return current;
    }

    @Override
    public ListIterator<E> iterator() {
        return new ListIteratorImpl<E>(this);
    }

    @Override
    public Link<E> getHead() {
        return head;
    }

    @Override
    public void setHead(final Link<E> head) {
        this.head = head;
    }

    // --------------------------------------------------------
    public static void main(final String[] args) {
        LinkList<Integer> ll = new LinkListImpl<Integer>();
        ll.insertHead(22);
        ll.insertHead(33);
        ll.insertHead(55);
        ll.insertHead(77);
        ll.insertHead(99);
        System.out.print("List (head --> tail): ");
        ll.displayList();

        while (!ll.isEmpty()) {
            Link<Integer> l = ll.deleteHead();
            System.out.print("Deleted ");
            System.out.println(l);
        }

        Link<Integer> l = ll.delete(33);
        if (l != null) {
            System.out.println("Deleted link with data: " + l.data);
        } else {
            System.out.println("Can't delete link");
        }
        System.out.print("List (head --> tail): ");
        ll.displayList();
    }
}
