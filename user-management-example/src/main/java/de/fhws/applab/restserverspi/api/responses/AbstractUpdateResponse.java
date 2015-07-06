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

package de.fhws.applab.restserverspi.api.responses;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.models.AbstractModel;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class AbstractUpdateResponse extends AbstractResponse
{
	protected AbstractUpdateResponse( )
	{
		super( );
	}

	public abstract static class AbstractUpdateResponseBuilder<T extends AbstractModel>
		extends AbstractResponse.AbstractResponseBuilder
	{
		protected long id;

		protected T modelToBeUpdated;

		public AbstractUpdateResponseBuilder( UriInfo uriInfo )
		{
			super( uriInfo );
		}

		public AbstractUpdateResponseBuilder update( long id )
		{
			this.id = id;
			return this;
		}

		public AbstractUpdateResponseBuilder withNewModel( T model )
		{
			this.modelToBeUpdated = model;
			return this;
		}

		@Override
		public Response build( )
		{
			try
			{
				if ( isUpdateAllowed( ) )
				{
					updateDatabase( );

					Response.ResponseBuilder responseBuilder = Response.noContent( );

					addSelfLink( responseBuilder );

					addLinkToCollection( responseBuilder );

					return responseBuilder.build( );
				}
				else
				{
					return Response.status( Response.Status.FORBIDDEN ).build( );
				}
			}
			catch ( Exception e )
			{
				return Response.status( Response.Status.NOT_FOUND ).build( );
			}
		}

		protected abstract void addSelfLink( Response.ResponseBuilder builder );

		protected abstract void addLinkToCollection( Response.ResponseBuilder responseBuilder );

		protected abstract void updateDatabase( ) throws DatabaseException;

		protected abstract boolean isUpdateAllowed( ) throws DatabaseException;
	}

}
