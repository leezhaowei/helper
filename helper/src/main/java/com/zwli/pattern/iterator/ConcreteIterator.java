package com.zwli.pattern.iterator;

import java.util.List;

public class ConcreteIterator<E> implements Iterator<E> {
    private final List<E> list;
    private int cursor;

    public ConcreteIterator(final List<E> list) {
        this.list = list;
        this.cursor = 0;
    }

    @Override
    public E next() {
        E e = null;
        if (this.hasNext()) {
            e = this.list.get(cursor);
            cursor++;
        }
        return e;
    }

    @Override
    public boolean hasNext() {
        if (cursor < list.size()) {
            return true;
        }
        return false;
    }

}
