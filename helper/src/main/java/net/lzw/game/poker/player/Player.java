package net.lzw.game.poker.player;

import java.util.ArrayList;
import java.util.List;

import net.lzw.game.poker.Card;

public class Player {

	private List<Card> cards;

	public Player() {
		cards = new ArrayList<>();
	}

	public List<Card> getCards() {
		return cards;
	}

	public void add(Card c) {
		cards.add(c);
	}

}
