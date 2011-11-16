package se.athega.lizell.gameoflife;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WhenUsingAGrid {
	private Grid grid;

	@Before
	public void setUp() throws Exception {
		final String theGrid =
				"#--\n" +
				"-#-\n" +
				"--#\n";
		grid = new Grid(theGrid);
	}

	@Test
	public void theNumberOfLiveNeighboursShouldBeCountable() {
		assertThat(grid.getLiveNeighboursAt(1, 1), is(2));
	}

	@Test
	public void theNumberOfLiveNeighboursShouldBeCountableForBoundaryCells() {
		assertThat("Upper left",  grid.getLiveNeighboursAt(0, 0), is(1));
		assertThat("Upper right", grid.getLiveNeighboursAt(0, 2), is(1));
		assertThat("Lower left",  grid.getLiveNeighboursAt(2, 0), is(1));
		assertThat("Lower right", grid.getLiveNeighboursAt(2, 2), is(1));
	}

	@Test
	public void theNumberOfLiveNeighboursShouldBeCountableInAllDirections() throws Exception {
		final String theGrid =
				"###\n" +
				"#-#\n" +
				"###\n";
		grid = new Grid(theGrid);

		assertThat(grid.getLiveNeighboursAt(1, 1), is(8));
	}

	@Test
	public void theNumberOfLiveNeighboursShouldNotIncludeSelf() throws Exception {
		final String theGrid =
				"###\n" +
				"###\n" +
				"###\n";
		grid = new Grid(theGrid);

		assertThat(grid.getLiveNeighboursAt(1, 1), is(8));
	}

	@Test
	public void aSpecifiedGridLocationCanBeUpdated() throws Exception {
		final String theGrid =
				"###\n" +
				"###\n" +
				"###\n";
		grid = new Grid(theGrid);

		final Cell cell = new Cell(Cell.Symbols.DEAD);
		grid.setCellAt(1, 1, cell);

		assertThat(grid.getCellAt(1, 1), sameInstance(cell));
	}
}
