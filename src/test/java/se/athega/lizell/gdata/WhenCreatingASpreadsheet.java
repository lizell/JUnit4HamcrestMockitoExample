package se.athega.lizell.gdata;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WhenCreatingASpreadsheet {
	private Spreadsheet spreadsheet;

	@Before
	public void setUp() throws Exception {
		spreadsheet = new Spreadsheet();
	}

	@Test
	public void theDefaultSpreadsheetKeyIsUsed() throws Exception {
		assertThat(spreadsheet.getKey().length(), is(greaterThan(10)));
	}

	@Test
	public void anyKeyCanBeUsed() throws Exception {
		spreadsheet = new Spreadsheet("anyKey");
		assertThat(spreadsheet.getKey(), containsString("anyKey"));
	}

	@Test
	public void theRowsCanBeRead() throws IOException {
		spreadsheet.setFetcher(mockFetcher());
		final List<Row> rows = spreadsheet.fetchRows();
		assertThat(rows.size(), is(1));
	}

	// We can even mock our own objects to isolate what we are testing
	private Fetcher mockFetcher() throws IOException {
		final Fetcher fetcher = mock(Fetcher.class);

		when(fetcher.getEntriesAsJson()).thenReturn(Arrays.asList(
				"{\"type\":\"text\",\"$t\":\"name: Christian, age: 12\"}"
		));

		return fetcher;
	}
}
