package se.athega.lizell.gameoflife;

public class Grid {
	final private static int DEFAULT_ROWS = 3;
	final private static int DEFAULT_COLS = 3;

	private Cell[][] cells;
	private int gridRows;
	private int gridCols;

	public Grid() {
		this(DEFAULT_ROWS, DEFAULT_COLS);
	}

	public Grid(final int rows, final int cols) {
		createDeadGrid(rows, cols);
	}

	public Grid(final String gridPattern) throws Exception {
		final String[] gridRows = gridPattern.split("\n");
		final int rows = gridRows.length;
		final int cols = gridRows[0].length();

		createCellMatrix(rows, cols);
		for (int row=0; row<rows; row++) {
			for (int col=0; col<cols; col++) {
				cells[row][col] = new Cell(String.valueOf(gridRows[row].charAt(col)));
			}
		}
	}

	public Cell getCellAt(final int row, final int col) {
		return cells[row][col];
	}

	public void setCellAt(final int row, final int col, final Cell cell) {
		cells[row][col] = cell;
	}

	public int getLiveNeighboursAt(final int row, final int col) {
		final int fromRow = row > 0 ? row-1 : 0;
		final int toRow = row < gridRows-1 ? row+1 : gridRows-1;
		final int fromCol = col > 0 ? col-1 : 0;
		final int toCol = col < gridCols-1 ? col+1 : gridCols-1;

		int liveNeighbours = 0;
		for (int r=fromRow; r<=toRow; r++) {
			for (int c=fromCol; c<=toCol; c++) {
				if (!(c == col && r == row) && getCellAt(r, c).isLive()) {
					liveNeighbours++;
				}
			}
		}

		return liveNeighbours;
	}

	public int getRows() {
		return gridRows;
	}

	public int getCols() {
		return gridCols;
	}

	private void createDeadGrid(final int rows, final int cols) {
		createCellMatrix(rows, cols);

		for (int row=0; row<rows; row++) {
			for (int col=0; col<cols; col++) {
				cells[row][col] = new Cell(Cell.Symbols.DEAD);
			}
		}
	}

	private void createCellMatrix(int rows, int cols) {
		cells = new Cell[rows][cols];
		gridRows = rows;
		gridCols = cols;
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder();
		for (int row=0; row<cells.length; row++) {
			for (int col=0; col<cells[row].length; col++) {
				result.append(getCellAt(row, col));
			}
			result.append("\n");
		}
		return result.toString();
	}
}
