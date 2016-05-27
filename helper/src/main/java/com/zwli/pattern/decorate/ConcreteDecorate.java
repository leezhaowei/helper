package com.zwli.pattern.decorate;

public class ConcreteDecorate extends Decorate {

    public ConcreteDecorate(final Component component) {
        super(component);
    }

    @Override
    public void doAnotherThing() {
        System.out.println(this.getClass().getName());
    }

}
