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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class AbstractDeleteResponse extends AbstractResponse
{
	protected AbstractDeleteResponse( )
	{
		super( );
	}

	public abstract static class AbstractDeleteResponseBuilder extends AbstractResponse.AbstractResponseBuilder
	{
		protected long id;

		public AbstractDeleteResponseBuilder( UriInfo uriInfo )
		{
			super( uriInfo );
		}

		public AbstractDeleteResponseBuilder delete( long id )
		{
			this.id = id;
			return this;
		}

		@Override
		public Response build( )
		{
			try
			{
				if ( isDeleteAllowed( ) )
				{
					deleteFromDatabase( );

					Response.ResponseBuilder responseBuilder = Response.noContent( );

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
				e.printStackTrace( );
				return Response.status( Response.Status.NOT_FOUND ).build( );
			}
		}

		protected abstract void addLinkToCollection( Response.ResponseBuilder responseBuilder );

		protected abstract void deleteFromDatabase( ) throws DatabaseException;

		protected abstract boolean isDeleteAllowed( ) throws DatabaseException;
	}
}
