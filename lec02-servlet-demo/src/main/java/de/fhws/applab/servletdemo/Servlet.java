package de.fhws.applab.servletdemo;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;

@WebServlet( name = "ServletExample", urlPatterns = { "/test" } )
public class Servlet extends javax.servlet.http.HttpServlet
{
	@Override
	protected void doPost( javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response ) throws javax.servlet.ServletException, IOException
	{}

	@Override
	protected void doGet( javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response )
		throws javax.servlet.ServletException, IOException
	{
		response.getWriter( ).print( "Hello World" );
	}
}
