package com.zwli.pattern.states.example_vote;

public class SpiteVoteState implements VoteState {

    @Override
    public void vote(final String user, final String voteItem, final VoteManager voteManager) {
        // String key = voteManager.getMapVote().get(user);
        // if(key!=null)
        final boolean hasUser = voteManager.getMapVote().containsKey(user);
        if (hasUser) {
            voteManager.getMapVote().remove(user);
        }
        System.out.println("You've been disqualifed due to spite voting");
    }

}
