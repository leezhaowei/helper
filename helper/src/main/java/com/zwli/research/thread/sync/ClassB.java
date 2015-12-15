package com.zwli.research.thread.sync;

public class ClassB {

    static {
        System.out.println("Instantiating > " + ClassB.class.getSimpleName());
    }

    public void test() {
        ClassA a = new ClassA();
        a.test();
    }
}
