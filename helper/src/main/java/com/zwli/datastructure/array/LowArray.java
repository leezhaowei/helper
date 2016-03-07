package com.zwli.datastructure.array;

public class LowArray {
    private final long[] a;

    public LowArray(final int size) {
        a = new long[size];
    }

    public void setElem(final int index, final long value) {
        a[index] = value;
    }

    public long getElem(final int index) {
        return a[index];
    }
}
