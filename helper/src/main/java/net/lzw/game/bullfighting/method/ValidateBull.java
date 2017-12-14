package net.lzw.game.bullfighting.method;

import java.util.Collections;
import java.util.List;

import net.lzw.game.poker.Card;
import net.lzw.game.poker.IMethod;

public class ValidateBull implements IMethod<Bull> {

	private static int[][] bullPos = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 1, 4 }, { 0, 2, 3 }, { 0, 2, 4 }, { 0, 3, 4 }, { 1, 2, 3 }, { 1, 2, 4 },
			{ 1, 3, 4 }, { 2, 3, 4 } };

	@Override
	public Bull validate(List<Card> cards) {
		Collections.<Card> sort(cards);

		int len = bullPos.length;
		for (int posIdx = 0; posIdx < len; posIdx++) {
			int[] arr = bullPos[posIdx];
			Card c0 = cards.get(arr[0]);
			Card c1 = cards.get(arr[1]);
			Card c2 = cards.get(arr[2]);

			int sum = c0.getValue() + c1.getValue() + c2.getValue();
			if (sum % 10 == 0) {
				System.out.println(cards);
				return new Bull(arr);
			}
		}

		return null;
	}

}
