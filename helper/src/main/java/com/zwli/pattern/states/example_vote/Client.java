package com.zwli.pattern.states.example_vote;

public class Client {

    public static void main(final String[] args) {
        final VoteManager vm = new VoteManager();
        for (int i = 0; i < 9; i++) {
            vm.vote("user", "A");
        }
    }

}
