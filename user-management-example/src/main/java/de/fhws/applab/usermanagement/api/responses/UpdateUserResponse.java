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

import de.fhws.applab.restserverspi.api.responses.AbstractUpdateResponse;
import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.usermanagement.api.filters.HttpBasicUserAuthorizationFilter;
import de.fhws.applab.usermanagement.database.DataAccessObjectsFactory;
import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.models.User;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by braunpet on 15.06.15.
 */
public class UpdateUserResponse extends AbstractUpdateResponse
{
	public static UpdateUserResponseBuilder newBuilder( UriInfo uriInfo )
	{
		return new UpdateUserResponseBuilder( uriInfo );
	}

	public static class UpdateUserResponseBuilder extends AbstractUpdateResponseBuilder<User>
	{
		protected User senderOfTheRequest;

		public UpdateUserResponseBuilder( UriInfo uriInfo )
		{
			super( uriInfo );
		}

		public UpdateUserResponseBuilder requestedByUser( ContainerRequestContext requestContext )
		{
			Object requestingUser = requestContext.getProperty( HttpBasicUserAuthorizationFilter.PROP_USER_OBJECT );
			this.senderOfTheRequest = ( User ) requestingUser;
			return this;
		}

		@Override protected void addLinkToCollection( Response.ResponseBuilder responseBuilder )
		{
			UserHyperlinks.addLinkToAllUsers( this.uriInfo, responseBuilder );
		}

		@Override protected void updateDatabase( ) throws DatabaseException
		{
			UserDAO userDAO = DataAccessObjectsFactory.getInstance( ).createUserDAO( );
			this.modelToBeUpdated.setLastModifiedAt( System.currentTimeMillis( ) );
			userDAO.updateUser( this.modelToBeUpdated );
		}

		@Override protected boolean isUpdateAllowed( ) throws DatabaseException
		{
			return this.senderOfTheRequest.getId( ) == this.id;
		}

		@Override protected void addSelfLink( Response.ResponseBuilder builder )
		{
			UserHyperlinks.addLinkWithRelType( this.uriInfo, builder, UserRelationTypes.REL_TYPE_SELF,
				this.modelToBeUpdated.getId( ) );
		}
	}
}
