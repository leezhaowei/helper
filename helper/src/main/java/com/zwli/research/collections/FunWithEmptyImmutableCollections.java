package com.zwli.research.collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link http://marxsoftware.blogspot.com/2008/05/using-collections-methods-emptylist.html}
 *
 * @author Create by Jeffery 2015/9/29
 *
 */
public class FunWithEmptyImmutableCollections {
    private Set<String> states;

    /**
     * Prepare the states data member with some sample names of states.
     */
    private void prepareStates() {
        states = new HashSet<String>();
        states.add("Alabama");
        states.add("Alaska");
        states.add("Arizona");
        states.add("Arkansas");
        states.add("California");
        states.add("Colorado");
        states.add("Connecticut");
        states.add("Delaware");
        states.add("Florida");
    }

    /**
     * Provide names of all states that begin with provided alphabet letter.
     *
     * @param firstLetter
     *            Letter for which matching state names are desired.
     * @return Set of names of states that begin with provided firstLetter.
     */
    private Set<String> getStatesStartingWithDesignatedLetter(final String firstLetter) {
        if ((firstLetter == null) || (firstLetter.isEmpty()) || (firstLetter.length() > 1)) {
            // return null;
            return Collections.emptySet();
        }

        final Set<String> matchingStates = new HashSet<String>();
        for (final String stateName : states) {
            if (stateName.startsWith(firstLetter.toUpperCase())) {
                matchingStates.add(stateName);
            }
        }

        // return Collections.unmodifiableSet(matchingStates);
        return matchingStates;
    }

    /**
     * Print out contents of provided Set.
     *
     * @param printTitle
     *            Title to print with set contents.
     * @param setToPrint
     *            Set whose contents should be printed.
     */
    public static void printSetContents(final String printTitle, final Set<String> setToPrint) {
        System.out.println("----- " + printTitle + "-----");
        for (final String setItem : setToPrint) {
            System.out.println(setItem);
        }
        System.out.println("--------------------");
    }

    /**
     * Add provided stateName to provided Set of states.
     *
     * @param states
     *            Set of states to which state name should be added.
     * @param stateName
     *            Name of state to be added.
     */
    public static void addArbitraryState(final Set<String> states, final String stateName) {
        states.add(stateName);
    }

    /**
     * Main executable method for running example.
     *
     * @param arguments
     *            Command-line arguments; none expected.
     */
    public static void main(final String[] arguments) {
        final FunWithEmptyImmutableCollections me = new FunWithEmptyImmutableCollections();
        me.prepareStates();
        Set<String> states = me.getStatesStartingWithDesignatedLetter("C");
        printSetContents("Happy Path: Designated Letter Matches", states);
        addArbitraryState(states, "Georgia");
        states = me.getStatesStartingWithDesignatedLetter("B");
        printSetContents("Not-So-Happy Path: Designated Letter Not Found", states);
        addArbitraryState(states, "Georgia");
        states = me.getStatesStartingWithDesignatedLetter("");
        printSetContents("Unhappy Path: null Returned", states);
        addArbitraryState(states, "Georgia");
    }
}
