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

import de.fhws.applab.restserverspi.api.responses.AbstractDeleteResponse;
import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.usermanagement.api.filters.HttpBasicUserAuthorizationFilter;
import de.fhws.applab.usermanagement.database.DataAccessObjectsFactory;
import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.models.User;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by braunpet on 14.06.15.
 */
public class DeleteUserResponse extends AbstractDeleteResponse
{
	public static final DeleteUserResponseBuilder newBuilder( UriInfo uriInfo )
	{
		return new DeleteUserResponseBuilder( uriInfo );
	}

	public static class DeleteUserResponseBuilder extends AbstractDeleteResponseBuilder
	{
		protected User senderOfTheRequest;

		public DeleteUserResponseBuilder( UriInfo uriInfo )
		{
			super( uriInfo );
		}

		public DeleteUserResponseBuilder requestedByUser( ContainerRequestContext requestContext )
		{
			Object requestingUser = requestContext.getProperty( HttpBasicUserAuthorizationFilter.PROP_USER_OBJECT );
			this.senderOfTheRequest = ( User ) requestingUser;
			return this;
		}

		@Override protected void deleteFromDatabase( ) throws DatabaseException
		{
			UserDAO userDAO = DataAccessObjectsFactory.getInstance( ).createUserDAO( );
			userDAO.deleteUser( this.id );
		}

		@Override protected void addLinkToCollection( Response.ResponseBuilder responseBuilder )
		{
			UserHyperlinks.addLinkToAllUsers( this.uriInfo, responseBuilder );
		}

		@Override protected boolean isDeleteAllowed( ) throws DatabaseException
		{
			return this.senderOfTheRequest.getId( ) == this.id;
		}
	}
}
