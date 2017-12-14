package net.lzw.game.poker.rules;

public class RegulationRule<T> implements IRule<T> {

	private T rule;

	public RegulationRule(T rule) {
		this.rule = rule;
	}

	@Override
	public T getRule() {
		return rule;
	}

}
