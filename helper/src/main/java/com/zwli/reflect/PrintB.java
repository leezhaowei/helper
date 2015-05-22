package com.zwli.reflect;

/**
 * created by zwli on Mar 31, 2014 Detailled comment
 */
public class PrintB implements IPrint {

    @Override
    public void print() {
        System.out.println("### " + this.getClass().getName());
    }

}
