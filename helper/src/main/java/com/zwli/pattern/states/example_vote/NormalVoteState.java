package com.zwli.pattern.states.example_vote;

public class NormalVoteState implements VoteState {

    @Override
    public void vote(final String user, final String voteItem, final VoteManager voteManager) {
        voteManager.getMapVote().put(user, voteItem);
        System.out.println("Vote successful");
    }

}
