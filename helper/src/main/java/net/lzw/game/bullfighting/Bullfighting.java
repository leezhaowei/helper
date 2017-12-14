package net.lzw.game.bullfighting;

import java.util.List;

import net.lzw.game.bullfighting.method.ValidateBull;
import net.lzw.game.bullfighting.player.BullPlayer;
import net.lzw.game.poker.Card;
import net.lzw.game.poker.PlayType;
import net.lzw.game.poker.Poker;
import net.lzw.game.poker.player.Player;

public class Bullfighting {

	private Player[] players;
	private Poker poker;

	public Bullfighting(Player[] players, Poker poker) {
		super();
		this.players = players;
		this.poker = poker;
	}

	public void deal() {
		int size = players.length;
		int dealLoop = 5;
		for (int dealIdx = 0; dealIdx < dealLoop; dealIdx++) {
			for (int playerIdx = 0; playerIdx < size; playerIdx++) {
				Player player = players[playerIdx];
				player.add(poker.getOneCard());
			}
		}
	}

	public void play() {
		int size = players.length;
		for (int playerIdx = 0; playerIdx < size; playerIdx++) {
			BullPlayer player = (BullPlayer) players[playerIdx];
			player.validate();
		}
	}

	public static void main(String[] args) {
		Poker poker = Poker.Builder.generatePoker(PlayType.BullFighting);
		Bullfighting bf = new Bullfighting(generatePlayer(1), poker);

		bf.deal();
		bf.play();

		// List<Card> list = poker.getCards();
		// print(list);
	}

	static Player[] generatePlayer(int size) {
		Player[] players = new Player[size];
		for (int idx = 0; idx < size; idx++) {
			players[idx] = new BullPlayer(new ValidateBull());
		}
		return players;
	}

	static void test() {
		for (int idx = 0; idx < 10; idx++) {
			Poker poker = Poker.Builder.generatePoker(PlayType.BullFighting);
			List<Card> list = poker.getCards();
			print(list);
		}
	}

	private static void print(List<Card> list) {
		for (int idx = 0; idx < list.size(); idx++) {
			System.out.println(list.get(idx));
		}
		System.out.println(list.size());
	}
}
