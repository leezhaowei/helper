package com.zwli.pattern.states;

public class Client {
    public static void main(final String[] args) {
        final State stateB = new ConcreteStateB();
        final State stateA = new ConcreteStateA();
        final Context context = new Context();
        context.setState(stateB);
        context.request("test");
        context.setState(stateA);
        context.request("test");
    }
}
