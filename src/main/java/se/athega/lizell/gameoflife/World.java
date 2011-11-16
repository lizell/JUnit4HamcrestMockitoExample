package se.athega.lizell.gameoflife;

public class World {
	private Grid grid;

	public World() {
		this.grid = new Grid();
	}

	public World(final int rows, final int cols) {
		this.grid = new Grid(rows, cols);
	}

	public World(final String gridPattern) throws Exception {
		this.grid = new Grid(gridPattern);
	}

	public Grid getGrid() {
		return grid;
	}

	public void createNextGeneration() {
		final Grid nextGrid = new Grid(grid.getRows(), grid.getCols());
		for (int row=0; row<grid.getRows(); row++) {
			for (int col=0; col<grid.getCols(); col++) {
				nextGrid.setCellAt(row, col, grid.getCellAt(row, col));

				if (grid.getCellAt(row, col).isLive()) {
					if (grid.getLiveNeighboursAt(row, col) < 2) {
						// Cell dies of under population
						nextGrid.setCellAt(row, col, new Cell(Cell.Symbols.DEAD));
					} else if (grid.getLiveNeighboursAt(row, col) > 3) {
						// Cell dies of over crowding
						nextGrid.setCellAt(row, col, new Cell(Cell.Symbols.DEAD));
					}
				} else if (grid.getLiveNeighboursAt(row, col) == 3) {
					// Cell is born by reproduction
					nextGrid.setCellAt(row, col, new Cell(Cell.Symbols.LIVE));
				}
			}
		}
		this.grid = nextGrid;
	}
}
