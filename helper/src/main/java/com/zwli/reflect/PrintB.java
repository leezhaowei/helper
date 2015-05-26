package com.zwli.reflect;

public class PrintB implements IPrint {

    @Override
    public void print() {
        System.out.println("### " + this.getClass().getName());
    }

}
