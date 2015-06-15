package de.fhws.applab.usermanagement.api;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import de.fhws.applab.restserverspi.api.annotations.UserAuthorization;
import de.fhws.applab.usermanagement.api.responses.*;
import de.fhws.applab.usermanagement.models.User;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path( "/users" )
public class UsersService
{
	@Context
	UriInfo uriInfo;

	@Context
	protected ContainerRequestContext requestContext;

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@UserAuthorization
	@ApiOperation(
		value = "Get a list of all users",
		notes = "The result can be filtered by name and date of creation", response = User[].class )
	@ApiResponses( {
		@ApiResponse( code = 200, message = "Response contains a collection of users" ) } )
	public Response getAllUser(
		@ApiParam( value = "Defines the number of requested elements", required = false, defaultValue = "10" )
		@DefaultValue( "10" ) @QueryParam( "size" ) int size,

		@ApiParam( value = "Defines how many elements should be skipped", required = false, defaultValue = "0" )
		@DefaultValue( "0" ) @QueryParam( "offset" ) int offset )
	{
		return GetAllUsersResponse.newBuilder( uriInfo )
			.requestedOffsetWas( offset )
			.requestedSizeWas( size )
			.build();
	}

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path( "{id: \\d+}" )
	@UserAuthorization
	public Response getUser( @PathParam( "id" ) long userId )
	{
		return SingleUserResponse.newBuilder( uriInfo ).forUserWithId( userId ).requestedByUser(
			requestContext ).build( );
	}

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path( "me" )
	@UserAuthorization
	public Response getTheRequestingUser( )
	{
		return SingleUserResponse.newBuilder( uriInfo ).forRequestingUser( requestContext ).build();
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response createUser( User user )
	{
		return CreateNewUserResponse.newBuilder( uriInfo ).withNewModel( user ).build();
	}

	@PUT
	@Consumes( MediaType.APPLICATION_JSON )
	@Path( "{id: \\d+}" )
	@UserAuthorization
	public Response updateUser( @PathParam( "id" ) long userId, User user )
	{
		return UpdateUserResponse.newBuilder( uriInfo ).requestedByUser( this.requestContext ).update( userId ).withNewModel( user ).build( );
	}

	@DELETE
	@Path( "{id: \\d+}" )
	@UserAuthorization
	public Response deleteUser( @PathParam( "id" ) long userId )
	{
		return DeleteUserResponse.newBuilder( uriInfo ).requestedByUser( this.requestContext ).delete( userId ).build( );
	}

}