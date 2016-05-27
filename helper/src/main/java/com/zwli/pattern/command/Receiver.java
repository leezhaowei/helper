package com.zwli.pattern.command;

public class Receiver {
    public void doSomething() {
        System.out.println(this.getClass().getName());
    }
}
