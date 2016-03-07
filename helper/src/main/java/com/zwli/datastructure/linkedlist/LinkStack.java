package com.zwli.datastructure.linkedlist;

public class LinkStack<E> {
    private final LinkList<E> list;

    public LinkStack() {
        list = new LinkListImpl<E>();
    }

    public void push(final E data) {
        list.insertHead(data);
    }

    public E pop() {
        return list.deleteHead().data;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void displayStack() {
        System.out.print("Stack (top --> bottom): ");
        list.displayList();
    }

    public static void main(final String[] args) {
        LinkStack<Integer> stack = new LinkStack<Integer>();
        stack.push(22);
        stack.push(44);
        stack.displayStack();

        stack.push(66);
        stack.push(88);
        stack.displayStack();

        stack.pop();
        stack.pop();
        stack.displayStack();
    }
}
