package com.zwli.pattern.states;

public class ConcreteStateA implements State {

    @Override
    public void handle(final String request) {
        System.out.println(this.getClass().getName() + " : " + request);
    }

}
