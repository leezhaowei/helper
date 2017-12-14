package net.lzw.game.poker;

public class Card implements Comparable<Card> {

	private Integer number;
	private Integer value;
	private CardSuit suit;

	public Card(Integer number, Integer value, CardSuit suit) {
		super();
		this.number = number;
		this.value = value;
		this.suit = suit;
	}

	public Integer getNumber() {
		return number;
	}

	public Integer getValue() {
		return value;
	}

	public CardSuit getSuit() {
		return suit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Card other = (Card) obj;
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		if (suit != other.suit) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Card o) {
		int order = 0;
		if (this.number.intValue() == o.number.intValue()) {
			order = this.suit.getValue().compareTo(o.suit.getValue());
		} else {
			order = this.number.compareTo(o.number);
		}
		return order;
	}

	@Override
	public String toString() {
		return number + ", " + suit + ", " + value + "\n";
	}

}
