package com.zwli.reflect;

public class PrintA implements IPrint {

    @Override
    public void print() {
        System.out.println("### " + this.getClass().getName());
    }
}
