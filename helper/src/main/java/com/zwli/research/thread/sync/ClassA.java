package com.zwli.research.thread.sync;

public class ClassA {

    static {
        System.out.println("Instantiating > " + ClassA.class.getSimpleName());
    }

    public void test() {
        synchronized (this) {
            System.out.println(this.getClass().getName());
        }
    }
}
