package se.athega.lizell.gdata;

import java.io.IOException;
import java.util.List;

public class DisplayData {
	final Spreadsheet spreadsheet;

	public DisplayData() {
		spreadsheet = new Spreadsheet();
	}

	public DisplayData(final String key) {
		spreadsheet = new Spreadsheet(key);
	}

	private void run() throws IOException {
		final List<Row> rows = spreadsheet.fetchRows();

		System.out.println("The data in spreadsheet " + spreadsheet.getKey() + " is:");
		for(final Row row : rows) {
			System.out.println(row.getContents());
		}
	}

	public static void main(final String[] args) throws IOException {
		final DisplayData displayData = (args.length > 0) ? new DisplayData(args[0]) : new DisplayData();
		displayData.run();

	}
}
