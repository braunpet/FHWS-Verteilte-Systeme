/*
 * Copyright (c) peter.braun@fhws.de
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.fhws.applab.usermanagement.api;

import com.wordnik.swagger.annotations.*;
import de.fhws.applab.restserverspi.api.annotations.UserAuthorization;
import de.fhws.applab.usermanagement.api.responses.*;
import de.fhws.applab.usermanagement.models.User;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.*;

@Path( "/users" )
@Api( value = "/users", description = "User Management API" )
public class UsersService
{
	@Context
	UriInfo uriInfo;

	@Context
	protected ContainerRequestContext requestContext;

	@Context
	protected Request request;

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@UserAuthorization
	@ApiOperation(
		value = "Get a list of all users",
		notes = "Authentication required. Pagination can be used by offset and size query parameters",
		response = User.class,
		responseContainer = "Array" )
	@ApiResponses( {
		@ApiResponse( code = 200, message = "Response contains a collection of users" ),
		@ApiResponse( code = 500, message = "Internal server error" ) } )
	public Response getAllUser(
		@ApiParam( value = "Defines the number of requested elements", required = false, defaultValue = "10" )
		@DefaultValue( "10" ) @QueryParam( "size" ) int size,

		@ApiParam( value = "Defines how many elements should be skipped", required = false, defaultValue = "0" )
		@DefaultValue( "0" ) @QueryParam( "offset" ) int offset )
	{
		return GetAllUsersResponse.newBuilder( uriInfo )
			.requestedOffsetWas( offset )
			.requestedSizeWas( size )
			.build( );
	}

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path( "{id: \\d+}" )
	@UserAuthorization
	@ApiOperation(
		value = "Get a single user by id",
		notes = "Authentication required.", response = User.class )
	@ApiResponses( {
		@ApiResponse( code = 200, message = "Response contains the requested user" ),
		@ApiResponse( code = 404, message = "The requested user does not exist" ),
		@ApiResponse( code = 500, message = "Internal server error" ) } )
	public Response getUser(
		@ApiParam( value = "The id of the user", required = true )
		@PathParam( "id" ) long userId )
	{
		return SingleUserResponse.newBuilder( uriInfo, request ).forUserWithId( userId ).requestedByUser(
			requestContext ).build( );
	}

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path( "me" )
	@UserAuthorization
	public Response getTheRequestingUser( )
	{
		return SingleUserResponse.newBuilder( uriInfo, request ).forRequestingUser( requestContext ).build( );
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response createUser( User user )
	{
		return CreateNewUserResponse.newBuilder( uriInfo ).withNewModel( user ).build( );
	}

	@PUT
	@Consumes( MediaType.APPLICATION_JSON )
	@Path( "{id: \\d+}" )
	@UserAuthorization
	public Response updateUser( @PathParam( "id" ) long userId, User user )
	{
		return UpdateUserResponse.newBuilder( uriInfo ).requestedByUser( this.requestContext ).update( userId )
			.withNewModel( user ).build( );
	}

	@DELETE
	@Path( "{id: \\d+}" )
	@UserAuthorization
	public Response deleteUser( @PathParam( "id" ) long userId )
	{
		return DeleteUserResponse.newBuilder( uriInfo ).requestedByUser( this.requestContext ).delete( userId )
			.build( );
	}

}