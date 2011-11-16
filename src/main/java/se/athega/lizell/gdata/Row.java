package se.athega.lizell.gdata;

import java.util.regex.Pattern;

public class Row {
	private final static Pattern TRIM_JSON = Pattern.compile(".*\\$t\":\"(.*)\"}");
	private final String input;
	private String contents;


	public Row(final String input) {
		this.input = input;
	}

	public String getContents() {
		if (contents == null) {
			contents = parse();
		}
		return contents;
	}

	private String parse() {
		if (input == null) return "";

		return TRIM_JSON.matcher(input).replaceAll("$1");
	}
}
