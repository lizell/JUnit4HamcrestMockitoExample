package se.athega.lizell.gdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {
	private final static String URL_PREFIX = "http://spreadsheets.google.com/feeds/list/";
	private final static String URL_POSTFIX = "/od6/public/basic?alt=json";
	private final static String DEFAULT_KEY = "0Aq1q3ft6OYMddGRxZkpBUml1XzNhVDVNT1FKTXBPQmc";

	private final String key;
	private ArrayList<Row> rows;
	private Fetcher fetcher;

	public Spreadsheet() {
		this(DEFAULT_KEY);
	}

	public Spreadsheet(final String key) {
		this.key = key;
	}

	public List<Row> fetchRows() throws IOException {
		if (rows == null) {
			rows = new ArrayList<Row>();
			List<String> entries = getFetcher().getEntriesAsJson();
			for(final String entry : entries) {
				rows.add(new Row(entry));
			}
		}
		return rows;
	}

	public String getKey() {
		return key;
	}

	private Fetcher getFetcher() {
		if (fetcher == null) {
			fetcher = new Fetcher(URL_PREFIX + key + URL_POSTFIX);
		}
		return fetcher;
	}

	// Used by tests
	void setFetcher(Fetcher fetcher) {
		this.fetcher = fetcher;
	}
}
