package net.lzw.game.poker;

public enum PlayType {

	BullFighting("Bull Fighting");

	private String label;

	PlayType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
