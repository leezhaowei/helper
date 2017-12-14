package net.lzw.game.bullfighting.player;

import net.lzw.game.bullfighting.method.Bull;
import net.lzw.game.poker.IMethod;
import net.lzw.game.poker.player.Player;

public class BullPlayer extends Player {

	private Bull bull;
	private IMethod<Bull> method;

	public BullPlayer(IMethod<Bull> method) {
		this.method = method;
	}

	public void validate() {
		bull = method.validate(getCards());
	}

	public Bull getBull() {
		return bull;
	}

	public IMethod<Bull> getMethod() {
		return method;
	}

}
