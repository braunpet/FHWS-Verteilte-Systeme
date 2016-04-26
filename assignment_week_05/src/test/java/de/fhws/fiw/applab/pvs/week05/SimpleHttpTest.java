package de.fhws.fiw.applab.pvs.week05;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class SimpleHttpTest
{

	protected String url;

	@Before
	public void startup( )
	{
		this.url = "http://localhost:8080/factorial/compute";
	}

	@Test
	public void test1( ) throws Exception
	{
		final URL uri = new URL( this.url + "?n=" + 5 );
		final HttpURLConnection connection = ( HttpURLConnection ) uri.openConnection( );
		final String result = IOUtils.toString( connection.getInputStream( ) );
		final int resultAsInt = Integer.parseInt( result );

		assertEquals( 120, resultAsInt );
	}

}
