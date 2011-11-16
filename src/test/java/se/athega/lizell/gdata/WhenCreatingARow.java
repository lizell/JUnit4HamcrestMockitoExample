package se.athega.lizell.gdata;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Originally created by @author chrliz at 2011-11-16 20.22
 */
public class WhenCreatingARow {
	@Test
	public void withNullValuesTheContentsAreEmpty() throws Exception {
		final Row row = new Row(null);
		assertThat(row.getContents(), is(""));
	}

	@Test
	public void withInvalidContentsTheInvalidContentsAreReturned() throws Exception {
		final Row row = new Row("invalid");
		assertThat(row.getContents(), is("invalid"));
	}

	@Test
	public void withValidContentsTheJsonIsTrimmedOff() throws Exception {
		final Row row = new Row("{\"type\":\"text\",\"$t\":\"name: Christian, age: 12\"}");
		assertThat(row.getContents(), is("name: Christian, age: 12"));
	}
}
