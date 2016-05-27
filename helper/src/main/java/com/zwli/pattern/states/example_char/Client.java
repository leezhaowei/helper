package com.zwli.pattern.states.example_char;

public class Client {
    public static void main(final String[] args) {
        final String str = "The intent of the STATE pattern is to distribute state-specific logic across classes that represent an object’s state.";
        final CharManager cm = new CharManager();
        cm.output(str);
    }
}
