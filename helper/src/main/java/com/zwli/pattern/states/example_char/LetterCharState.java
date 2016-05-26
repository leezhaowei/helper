package com.zwli.pattern.states.example_char;

public class LetterCharState implements CharState {

    @Override
    public void handle(final Character c, final CharManager cm) {
        cm.getStack().push(c);
    }

}
