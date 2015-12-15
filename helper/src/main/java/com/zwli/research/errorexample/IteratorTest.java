package com.zwli.research.errorexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

    public static void main(String[] args) {
        // errorTest1();
        errorTest2();
    }

    static void errorTest2() {
        Collection<Face> faces = Arrays.asList(Face.values());
        for (Iterator<Face> i = faces.iterator(); i.hasNext();) {
            for (Iterator<Face> j = faces.iterator(); j.hasNext();) {
                System.out.println(i.next() + " " + j.next());
            }
        }
    }

    static void errorTest1() {
        Collection<Suit> suits = Arrays.asList(Suit.values());
        Collection<Rank> ranks = Arrays.asList(Rank.values());

        List<Card> deck = new ArrayList<Card>();
        for (Iterator<Suit> i = suits.iterator(); i.hasNext();) {
            // Suit t = i.next();
            for (Iterator<Rank> j = ranks.iterator(); j.hasNext();) {
                deck.add(new Card(i.next(), j.next())); // i.next() should not be here to invoke, it will be invoked
                                                        // about ranks.size times which not correct;
                                                        // "Suit t = i.next();" this line is correct.
                System.out.println("Deck size: " + deck.size());
            }
        }
    }

    static enum Suit {
        CLUB, DIAMOND, HEART, SPADE
    }

    static enum Rank {
        ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    static class Card {
        Suit suit;
        Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }
    }

    static enum Face {
        ONE, TWO, THREE, FOUR, FIVE, SIX
    }
}
