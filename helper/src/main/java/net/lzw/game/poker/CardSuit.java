package net.lzw.game.poker;

public enum CardSuit {

	SPADE(4), HEART(3), DIAMOND(2), CLUB(1);

	private Integer value;

	CardSuit(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
}
