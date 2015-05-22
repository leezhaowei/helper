package com.zwli.datastructure;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class StackArrayImpl<E> implements IStack<E> {

    private E[] arr;

    private int size;

    public StackArrayImpl() {
        arr = (E[]) new Object[2];
    }

    private void resize(int capacity) {
        E[] tmp = (E[]) new Object[capacity];
        System.arraycopy(arr, 0, tmp, 0, size);
        arr = tmp;
        tmp = null;
    }

    @Override
    public IStack<E> push(E e) {
        if (arr.length == size) {
            resize(arr.length * 2);
        }
        arr[size++] = e;
        return this;
    }

    @Override
    public E pop() {
        if (size == 0) {
            return null;
        }
        E e = arr[--size];
        arr[size] = null;
        if (size > 0 && size == arr.length / 4) {
            resize(arr.length / 2);
        }
        return e;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    public static void main(String[] args) {
        IStack<Integer> stack = new StackArrayImpl<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(3);
        stack.push(4);
        System.out.println(4 == stack.pop());
        System.out.println(3 == stack.pop());
        System.out.println(3 == stack.pop());
        System.out.println(2 == stack.pop());
        System.out.println(1 == stack.pop());
    }
}
