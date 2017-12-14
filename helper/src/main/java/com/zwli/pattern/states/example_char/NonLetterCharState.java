package com.zwli.pattern.states.example_char;

public class NonLetterCharState implements CharState {

    @Override
    public void handle(final Character c, final CharManager cm) {
        while (!cm.getStack().empty()) {
            cm.getQueue().add(cm.getStack().pop());
        }
        if (c != null) {
            cm.getQueue().add(c);
        }
    }

}
