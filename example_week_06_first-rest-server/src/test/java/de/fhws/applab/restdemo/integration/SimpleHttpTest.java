package de.fhws.applab.restdemo.integration;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleHttpTest
{

	protected String url;

	@Before
	public void startup( )
	{
		this.url = "http://localhost:8080/demo";
	}

	@Test
	@Ignore
	public void test1( ) throws Exception
	{

		final CloseableHttpClient httpclient = HttpClients.createDefault( );
		final HttpGet httpGet = new HttpGet( this.url + "/api/accounts" );
		final CloseableHttpResponse response1 = httpclient.execute( httpGet );

		try
		{
			final StatusLine status = response1.getStatusLine( );
			assertEquals( HttpStatus.SC_OK, status.getStatusCode( ) );
		}
		finally
		{
			response1.close( );
		}
	}

}
