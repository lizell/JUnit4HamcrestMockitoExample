package se.athega.lizell.gameoflife;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static se.athega.lizell.gameoflife.IsDead.dead;

public class WhenCreatingAGrid {
	private Grid grid;

	@Before
	public void setUp() {
		grid = new Grid();
	}

	@Test
	public void theDefaultGridShouldBe3By3DeadCells() throws Exception {
		final Matcher<Cell> deadly = hasProperty("symbol", is(Cell.Symbols.DEAD.toString()));

		for (int row=0; row<3; row++) {
			for (int col=0; col<3; col++) {
				assertThat(grid.getCellAt(row, col), is(dead())); // Custom matcher
				assertThat(grid.getCellAt(row, col), is(deadly)); // Dedicated matcher
			}
		}
	}

	@Test
	public void theGridCanBeOfCustomSize() throws Exception {
		final int rows = 13;
		final int cols = 10;
		final Grid customGrid = new Grid(rows, cols);
		assertThat(customGrid.getCellAt(rows - 1, cols - 1), any(Cell.class));
	}

	@Test
	public void theGridCanBeCreatedFromAString() throws Exception {
		final String theGrid =
				"#--\n" +
				"-#-\n" +
				"--#\n";
		final Grid stringGrid = new Grid(theGrid);
		assertThat(stringGrid.toString(), is(theGrid));
	}
}

class IsDead extends TypeSafeMatcher<Cell> {
	@Override
	public boolean matchesSafely(final Cell cell) {
		return cell.isDead();
	}

	public void describeTo(final Description description) {
		description.appendText("dead");
	}

	@Factory
	public static <T> Matcher<Cell> dead() {
		return new IsDead();
	}
}
