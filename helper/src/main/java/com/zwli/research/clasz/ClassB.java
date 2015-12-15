package com.zwli.research.clasz;

public class ClassB extends ParentClass {

    private int age;

    public ClassB(String name, String password) {
        super(name, password);
    }

    public ClassB(String name, String password, int age) {
        super(name, password);
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
