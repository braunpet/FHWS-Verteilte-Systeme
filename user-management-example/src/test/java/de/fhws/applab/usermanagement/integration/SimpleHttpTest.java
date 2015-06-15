package de.fhws.applab.usermanagement.integration;

import static org.junit.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

public class SimpleHttpTest {

	protected String url;

	@Before
	public void startup() {
		this.url = "http://localhost:8080/demo";
	}

	@Test
	public void test1() throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(this.url + "/api/accounts");
		CloseableHttpResponse response1 = httpclient.execute(httpGet);

		try {
			StatusLine status = response1.getStatusLine();
			assertEquals(HttpStatus.SC_OK, status.getStatusCode());
		} finally {
			response1.close();
		}
	}

}
