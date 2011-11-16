package se.athega.lizell.gameoflife;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WhenCreatingAWorld {
	private World world;

	@Before
	public void setUp() throws Exception {
		world = new World();
	}

	@Test
	public void theDefaultGridContainsDeadCellsOnly() {
		final String defaultGrid = "---\n---\n---\n";
		assertThat(world.getGrid().toString(), is(defaultGrid));
	}

	@Test
	public void theGridCanBeOfCustomSize() throws Exception {
		final int rows = 2;
		final int cols = 2;
		world = new World(rows, cols);
		final String expectedGrid = "--\n--\n";
		assertThat(world.getGrid().toString(), Matchers.is(expectedGrid));
	}
}
