package com.zwli.pattern.states.example_vote;

import java.util.HashMap;
import java.util.Map;

public class VoteManager {

    private VoteState state;
    private final Map<String, String> mapVote = new HashMap<String, String>();
    private final Map<String, Integer> mapVoteCount = new HashMap<String, Integer>();

    public Map<String, String> getMapVote() {
        return mapVote;
    }

    public void vote(final String user, final String voteItem) {
        Integer oldVoteCount = mapVoteCount.get(user);
        if (oldVoteCount == null) {
            oldVoteCount = 0;
        }
        oldVoteCount += 1;
        mapVoteCount.put(user, oldVoteCount);

        if (oldVoteCount == 1) {
            state = new NormalVoteState();
        } else if (oldVoteCount > 1 && oldVoteCount < 5) {
            state = new RepeatVoteState();
        } else if (oldVoteCount >= 5 && oldVoteCount < 8) {
            state = new SpiteVoteState();
        } else if (oldVoteCount > 8) {
            state = new BlackVoteState();
        }
        state.vote(user, voteItem, this);
    }
}
