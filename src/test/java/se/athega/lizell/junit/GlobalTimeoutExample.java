package se.athega.lizell.junit;


import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.Timeout;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class GlobalTimeoutExample {
	@Rule
	public MethodRule tmpFolder = new Timeout(1000);

	@Test
	public void shouldBeQuick() throws Exception {
		assertThat("a", not(is("b")));
	}

	@Ignore("Uncomment if you want to see the timeout in action")
	@Test
	public void maybeNotSoQuick() throws Exception {
		while (true) {
			assertThat("a", not(is("b")));
		}
	}
}
