package net.lzw.game.calculator;

public abstract class AbstractExpression implements IExpression {

	private String left;
	private String right;
	private String operation;

	public AbstractExpression(String left, String right, String operation) {
		super();
		this.left = left;
		this.right = right;
		this.operation = operation;
	}

	@Override
	public String left() {
		return left;
	}

	@Override
	public String right() {
		return right;
	}

	@Override
	public String operation() {
		return operation;
	}

}
