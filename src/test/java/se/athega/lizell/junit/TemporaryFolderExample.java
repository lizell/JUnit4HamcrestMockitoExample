package se.athega.lizell.junit;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TemporaryFolderExample {
	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();

	private File testFile;

	@Before
	public void setUpTestDir() throws IOException {
		testFile = tmpFolder.newFile("test.txt");
		final FileWriter writer = new FileWriter(testFile);
		writer.append("ThisIsFun");
		writer.close();
	}

	@Test
	public void shouldContainFunText() throws Exception {
		final BufferedReader reader = new BufferedReader(new FileReader(testFile));
		assertThat(reader.readLine(), is("ThisIsFun"));
	}
}
