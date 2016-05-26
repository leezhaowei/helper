package com.zwli.pattern.states;

public class Context {

    private State state;

    public void request(final String request) {
        state.handle(request);
    }

    public void setState(final State state) {
        this.state = state;
    }

}
