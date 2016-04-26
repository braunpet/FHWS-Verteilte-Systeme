package de.fhws.applab.restdemo.api;

import de.fhws.applab.restdemo.models.Person;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path( "/accounts" )
public class AccountsService
{

	@Context
	UriInfo uriInfo;

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Person getPerson( )
	{
		return new Person( "James", "Bond" );
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response post( Person person )
	{
		System.out.println( person );
		return Response.created( uriInfo.getAbsolutePathBuilder( ).path( person.getLastName( ) ).build( ) ).build( );
	}

}