package com.zwli.pattern.states.example_char;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * <b>Input: </b>The intent of the STATE pattern is to distribute state-specific logic across classes that represent an object’s state.<br>
 * <b>Output: </b>ehT tnetni fo eht ETATS nrettap si ot etubirtsid etats-cificeps cigol ssorca sessalc taht tneserper na tcejbo’s etats.
 * @author Jeffery Li
 */
public class CharManager {

    private final Stack<Character> stack = new Stack<Character>();
    private final Queue<Character> queue = new ArrayDeque<Character>();

    public Stack<Character> getStack() {
        return stack;
    }

    public Queue<Character> getQueue() {
        return queue;
    }

    public void output(final String str) {
        if (str == null) {
            return;
        }

        char c;
        CharState state;
        boolean isChar;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            isChar = Character.isAlphabetic(c);
            if (isChar) {
                state = new LetterCharState();
            } else {
                state = new NonLetterCharState();
            }
            state.handle(c, this);
        }
        if (!stack.isEmpty()) {
            state = new NonLetterCharState();
            state.handle(null, this);
        }

        final StringBuffer info = new StringBuffer();
        while (!queue.isEmpty()) {
            info.append(queue.poll());
        }
        System.out.println(info.toString());
    }
}
