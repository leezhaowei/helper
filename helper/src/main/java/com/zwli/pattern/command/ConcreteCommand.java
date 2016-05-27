package com.zwli.pattern.command;

public class ConcreteCommand implements Command {
    private final Receiver receiver;

    public ConcreteCommand(final Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getName());
        this.receiver.doSomething();
    }

}
