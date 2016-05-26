package com.zwli.pattern.states.example_char;

public class Client {
    public static void main(final String[] args) {
        final String str = "the type of elements held in this collection";
        final CharManager cm = new CharManager();
        cm.output(str);
    }
}
