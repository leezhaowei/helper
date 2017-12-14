package net.lzw.game.poker.rules;

import net.lzw.game.poker.PlayType;

public class RuleBuilder {

	public static IRule<Integer> generateAmountRule(PlayType type) {

		switch (type) {
		case BullFighting:
			return new RegulationRule<>(52);
		default:
		}

		return null;
	}

	public static IRule<Boolean> generateJokerRule(PlayType type) {

		switch (type) {
		case BullFighting:
			return new RegulationRule<>(Boolean.FALSE);
		default:
		}

		return null;
	}

	public static IRule<int[]> generateValueRule(PlayType type) {

		switch (type) {
		case BullFighting:
			return new RegulationRule<>(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 });
		default:
		}

		return null;
	}

}
