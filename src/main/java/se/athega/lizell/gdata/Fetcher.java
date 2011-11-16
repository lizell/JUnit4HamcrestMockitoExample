package se.athega.lizell.gdata;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fetcher {
	private final static Pattern POOR_MANS_JSON_PARSER = Pattern.compile("\"content\":(\\{[^\\}]+\\})");
	private final String url;
	private List<String> entries;
	private HttpMethod httpMethod;
	private HttpClient httpClient;

	public Fetcher(final String url) {
		this.url = url;
	}

	public List<String> getEntriesAsJson() throws IOException {
		if (entries == null) {
			fetch();
		}
		return entries;
	}

	void fetch() throws IOException {
		final HttpClient client = getHttpClient();
		final HttpMethod method = getHttpMethod();

		String result;
		try {
			client.executeMethod(method);
			result = method.getResponseBodyAsString();
		} catch (final IOException e) {
			throw e;
		} finally {
			method.releaseConnection();
		}

		parseEntries(result);
	}

	void parseEntries(final String result) {
		final Matcher matcher = POOR_MANS_JSON_PARSER.matcher(result);

		entries = new ArrayList<String>();
		while (matcher.find()) {
			entries.add(matcher.group(1));
		}
	}

	// For mocking demonstration purposes
	private HttpMethod getHttpMethod() {
		if (httpMethod == null) {
			httpMethod = new GetMethod(url);
		}
		return httpMethod;
	}
	void setHttpMethod(final HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	private HttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = new HttpClient();
		}
		return httpClient;
	}
	void setHttpClient(final HttpClient httpClient) {
		this.httpClient = httpClient;
	}
}
