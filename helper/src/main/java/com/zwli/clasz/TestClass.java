package com.zwli.clasz;

public class TestClass {

    public static void main(String[] args) {

        ParentClass a = new ClassA("a", "b", "c");
        ParentClass b = new ClassB("aa", "bb", 11);

        System.out.println(ClassA.class == a.getClass());
        System.out.println(ClassA.class == b.getClass());
        System.out.println(ClassB.class == b.getClass());
    }

}
