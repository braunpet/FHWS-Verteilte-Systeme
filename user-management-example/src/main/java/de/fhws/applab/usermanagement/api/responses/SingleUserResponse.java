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

package de.fhws.applab.usermanagement.api.responses;

import de.fhws.applab.restserverspi.api.responses.AbstractGetResponse;
import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.usermanagement.api.filters.HttpBasicUserAuthorizationFilter;
import de.fhws.applab.usermanagement.database.DataAccessObjectsFactory;
import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.models.User;
import org.apache.log4j.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by braunpet on 14.06.15.
 */
public class SingleUserResponse extends AbstractGetResponse
{
	private static final Logger LOGGER = Logger.getLogger( SingleUserResponse.class );

	public static SingleUserResponseBuilder1 newBuilder( UriInfo uriInfo )
	{
		return new SingleUserResponseBuilder1( uriInfo );
	}

	public static class SingleUserResponseBuilder1
	{
		protected UriInfo uriInfo;

		protected SingleUserResponseBuilder1( UriInfo uriInfo )
		{
			this.uriInfo = uriInfo;
		}

		public SingleUserResponseBuilder2 forUserWithId( long userId )
		{
			return new SingleUserResponseBuilder2( this.uriInfo, userId );
		}

		public SingleUserResponseBuilder3 forRequestingUser( ContainerRequestContext requestContext )
		{
			Object requestingUser = requestContext.getProperty( HttpBasicUserAuthorizationFilter.PROP_USER_OBJECT );
			User theUser = ( User ) requestingUser;
			return new SingleUserResponseBuilder3( this.uriInfo, theUser );
		}
	}

	public abstract static class SingleUserResponseBuilder4 extends SingleResponseBuilder<User>
	{
		protected User senderOfTheRequest;

		protected SingleUserResponseBuilder4( UriInfo uriInfo )
		{
			super( uriInfo );
		}

		@Override protected void addAdditionalLinks( Response.ResponseBuilder builder )
		{
			if ( userWantsToSeeHisProfile( ) )
			{
				addLinkToUpdateUser( builder );
				addLinkToDeleteUser( builder );
			}

			addSelfLink( builder );
			UserHyperlinks.addLinkToAllUsers( this.uriInfo, builder );
		}

		protected boolean userWantsToSeeHisProfile( )
		{
			return this.senderOfTheRequest.getId( ) == this.result.getId( );
		}

		@Override protected void addCacheControl( Response.ResponseBuilder responseBuilder )
		{
			long lastModifiedDate = this.result.getLastModifiedAt( );
			String eTagAsString = Long.toString( lastModifiedDate );
			EntityTag eTag = new EntityTag( eTagAsString );
			responseBuilder.tag( eTag );
		}

		private void addSelfLink( Response.ResponseBuilder builder )
		{
			UserHyperlinks
				.addLinkWithRelType( this.uriInfo, builder, UserRelationTypes.REL_TYPE_SELF, this.result.getId( ) );
		}

		private void addLinkToUpdateUser( Response.ResponseBuilder builder )
		{
			UserHyperlinks.addLinkWithRelType( this.uriInfo, builder, UserRelationTypes.REL_TYPE_UPDATE_USER,
				this.result.getId( ) );
		}

		private void addLinkToDeleteUser( Response.ResponseBuilder builder )
		{
			UserHyperlinks.addLinkWithRelType( this.uriInfo, builder, UserRelationTypes.REL_TYPE_DELETE_USER,
				this.result.getId( ) );
		}

	}

	public static class SingleUserResponseBuilder2 extends SingleUserResponseBuilder4
	{
		private long userId;

		protected SingleUserResponseBuilder2( UriInfo uriInfo, long userId )
		{
			super( uriInfo );
			this.userId = userId;
		}

		public SingleUserResponseBuilder2 requestedByUser( ContainerRequestContext requestContext )
		{
			Object requestingUser = requestContext.getProperty( HttpBasicUserAuthorizationFilter.PROP_USER_OBJECT );
			this.senderOfTheRequest = ( User ) requestingUser;
			return this;
		}

		@Override protected User loadFromDatabase( ) throws DatabaseException
		{
			LOGGER.debug( "Requesting user with id " + this.userId );

			UserDAO userDAO = DataAccessObjectsFactory.getInstance( ).createUserDAO( );
			return userDAO.loadUser( this.userId );
		}
	}

	public static class SingleUserResponseBuilder3 extends SingleUserResponseBuilder4
	{
		protected SingleUserResponseBuilder3( UriInfo uriInfo, User requestingUser )
		{
			super( uriInfo );
			this.senderOfTheRequest = requestingUser;
		}

		@Override protected User loadFromDatabase( ) throws DatabaseException
		{
			return this.senderOfTheRequest;
		}
	}
}
