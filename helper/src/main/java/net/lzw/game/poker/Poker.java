package net.lzw.game.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.lzw.game.exception.GameException;
import net.lzw.game.poker.rules.IRule;
import net.lzw.game.poker.rules.RuleBuilder;

public class Poker {

	private List<Card> cards;
	private List<Card> dealtCards;

	private Poker(List<Card> cards) {
		this.cards = cards;
		dealtCards = new ArrayList<>();
	}

	public Card getOneCard() {
		if (cards.size() > 0) {
			Card c = cards.remove(0);
			dealtCards.add(c);
			return c;
		} else {
			throw new GameException("There is no card to deal.");
		}
	}

	public List<Card> getCards() {
		return cards;
	}

	public static class Builder {

		public static Poker generatePoker(PlayType type) {

			IRule<Integer> ruleAmount = RuleBuilder.generateAmountRule(type);
			IRule<Boolean> ruleJoker = RuleBuilder.generateJokerRule(type);
			IRule<int[]> ruleValue = RuleBuilder.generateValueRule(type);

			List<Card> list = new ArrayList<>(ruleAmount.getRule().intValue());
			generateCards(list, ruleValue);
			addJoker(list, ruleJoker);
			shuffle(list);

			// return new Poker(Collections.unmodifiableList(list));
			return new Poker(list);
		}

		private static void addJoker(List<Card> list, IRule<Boolean> ruleJoker) {
			if (ruleJoker.getRule().booleanValue()) {
				// TODO: add joker
			}
		}

		private static void shuffle(List<Card> list) {
			Collections.shuffle(list);
		}

		private static void generateCards(List<Card> list, IRule<int[]> ruleValue) {
			CardSuit[] suits = CardSuit.values();
			for (int cardIdx = 0; cardIdx < 13; cardIdx++) {
				for (CardSuit suit : suits) {
					list.add(new Card(Integer.valueOf(cardIdx + 1), Integer.valueOf(ruleValue.getRule()[cardIdx]), suit));
				}
			}
		}

	}
}
