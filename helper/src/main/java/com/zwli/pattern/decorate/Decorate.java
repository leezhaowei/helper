package com.zwli.pattern.decorate;

public abstract class Decorate implements Component {
    private final Component component;

    public Decorate(final Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        this.component.doSomething();
        doAnotherThing();
    }

    abstract public void doAnotherThing();

}
