package se.athega.lizell.gameoflife;

public class Cell {
	public enum Symbols {
		LIVE("#"),
		DEAD("-");

		final private String sym;

		Symbols(final String sym) {
			this.sym = sym;
		}

		@Override
		public String toString() {
			return sym;
		}
	}
	final private String symbol;

	public Cell() throws Exception {
		this(Symbols.DEAD);
	}

	public Cell(final Symbols symbol) {
		this.symbol = symbol.toString();
	}

	public Cell(final String symbol) throws Exception {
		if (Symbols.LIVE.toString().equals(symbol) || Symbols.DEAD.toString().equals(symbol)) {
			this.symbol = symbol;
		} else {
			throw new Exception("Invalid symbol given: " + symbol);
		}
	}

	public String getSymbol() {
		return symbol;
	}

	public boolean isDead() {
		return Symbols.DEAD.toString().equals(symbol);
	}

	public boolean isLive() {
		return Symbols.LIVE.toString().equals(symbol);
	}

	@Override
	public String toString() {
		return symbol;
	}
}
