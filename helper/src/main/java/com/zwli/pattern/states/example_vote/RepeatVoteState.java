package com.zwli.pattern.states.example_vote;

public class RepeatVoteState implements VoteState {

    @Override
    public void vote(final String user, final String voteItem, final VoteManager voteManager) {
        System.out.println("Please don't vote repetitively");
    }

}
