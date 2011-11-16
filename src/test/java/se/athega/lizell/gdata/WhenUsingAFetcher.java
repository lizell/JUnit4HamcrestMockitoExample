package se.athega.lizell.gdata;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Needed for the @Mock annotation below
@RunWith(MockitoJUnitRunner.class)
public class WhenUsingAFetcher {
	private Fetcher fetcher;
	private String jsonData;

	// Same as client = mock(HttpClient.class);
	@Mock private HttpClient client;

	@Before
	public void setUp() throws IOException {
		// Convenient way reading a file regardless of where the project is checked out
		jsonData = IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream("gdata_two_entries.json"));

		// Mock the GetMethod
		GetMethod method = mock(GetMethod.class);

		// Return the JSON when getResponseBodyAsString is called
		when(method.getResponseBodyAsString()).thenReturn(jsonData);

		// Create the fetcher and make it use our mocked instances
		fetcher = new Fetcher("dummy");
		fetcher.setHttpClient(client);
		fetcher.setHttpMethod(method);
	}

	@Test
	public void twoEntriesAreReturnedUsingMockedHttpClient() throws Exception {
		assertThat(fetcher.getEntriesAsJson().size(), is(2));
	}

	@Test
	public void twoEntriesAreReturnedFromGoogleJson() throws Exception {
		fetcher.parseEntries(jsonData);

		assertThat(fetcher.getEntriesAsJson(), hasItems(
				"{\"type\":\"text\",\"$t\":\"name: Christian, age: 12\"}",
				"{\"type\":\"text\",\"$t\":\"name: Tintin, age: 13\"}"
		));
	}
}
