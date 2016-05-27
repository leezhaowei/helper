package com.zwli.pattern.decorate;

public class ConcreteComponent implements Component {

    @Override
    public void doSomething() {
        System.out.println(this.getClass().getName());
    }

}
