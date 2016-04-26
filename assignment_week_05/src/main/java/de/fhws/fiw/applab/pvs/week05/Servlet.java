package de.fhws.fiw.applab.pvs.week05;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name = "ServletFactorial", urlPatterns = { "/compute" } )
public class Servlet extends javax.servlet.http.HttpServlet
{
	@Override
	protected void doGet( final HttpServletRequest request, final HttpServletResponse response )
		throws ServletException, IOException
	{
		try
		{
			final String paramAsString = request.getParameter( "n" );

			if ( paramAsString != null )
			{
				final int paramAsInt = Integer.parseInt( paramAsString );
				response.getWriter( ).print( factorial( paramAsInt ) );
			}
		}
		catch ( final NumberFormatException e )
		{
			response.getWriter( ).print( "Parameter must be a number" );
		}
		catch ( final IOException e )
		{
			e.printStackTrace( );
		}
	}

	private int factorial( final int n )
	{
		int returnValue = 1;

		for ( int i = 1; i <= n; i++ )
		{
			returnValue *= i;
		}

		return returnValue;
	}
}
