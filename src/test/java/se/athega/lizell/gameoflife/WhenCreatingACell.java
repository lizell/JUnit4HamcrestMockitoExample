package se.athega.lizell.gameoflife;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.text.StringStartsWith.startsWith;
import static org.junit.Assert.assertThat;

public class WhenCreatingACell {
	private Cell defaultCell;
	private Cell aliveCell;

	@Rule public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		defaultCell = new Cell();
		aliveCell = new Cell(Cell.Symbols.LIVE);
	}

	@Test
	public void theDefaultValueShouldBeDead() {
		assertThat(defaultCell.getSymbol(), is(Cell.Symbols.DEAD.toString()));
	}

	@Test
	public void aDeadCellIsRepresentedByADash() {
		assertThat(defaultCell.getSymbol(), is("-"));
	}

	@Test
	public void anAliveCellIsRepresentedByAHash() {
		assertThat(aliveCell.getSymbol(), is("#"));
	}

	@Test
	public void withABogusSymbolAnAcceptableErrorMessageIsThrown() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage(startsWith("Invalid"));
		new Cell("Bogus");
	}
}
