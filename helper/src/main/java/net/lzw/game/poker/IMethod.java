package net.lzw.game.poker;

import java.util.List;

public interface IMethod<T> {

	public T validate(List<Card> cards);

}
