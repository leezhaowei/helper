package com.zwli.pattern.states.example_vote;

public interface VoteState {

    public void vote(String user, String voteItem, VoteManager voteManager);

}
