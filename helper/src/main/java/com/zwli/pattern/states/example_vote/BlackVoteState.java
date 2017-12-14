package com.zwli.pattern.states.example_vote;

public class BlackVoteState implements VoteState {

    @Override
    public void vote(final String user, final String voteItem, final VoteManager voteManager) {
        System.out.println("The user is in black list, should be forbidden to use vote system");
    }

}
