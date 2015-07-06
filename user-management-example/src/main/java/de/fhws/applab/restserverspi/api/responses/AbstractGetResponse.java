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

import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractGetResponse extends AbstractResponse
{
	protected AbstractGetResponse( )
	{
	}

	public abstract static class SingleResponseBuilder<T extends AbstractModel>
		extends AbstractResponse.AbstractResponseBuilder
	{
		protected T result;

		protected Request request;

		protected SingleResponseBuilder( UriInfo uriInfo, Request request )
		{
			super( uriInfo );
			this.request = request;
		}

		public final Response build( )
		{
			try
			{
				this.result = loadFromDatabase( );

				if ( this.result != null )
				{
					if ( isPreconditionMet( this.request, this.result ) == false )
					{
						return Response.notModified( ).build( );
					}
					else
					{
						ResponseBuilder builder = Response.ok( this.result );

						addCacheControl( builder );
						addAdditionalLinks( builder );

						return builder.build( );
					}
				}
				else
				{
					return Response.status( Response.Status.NOT_FOUND ).build( );
				}
			}
			catch ( Exception e )
			{
				e.printStackTrace( );
				return Response.serverError( ).build( );
			}
		}

		protected abstract T loadFromDatabase( ) throws DatabaseException;

		protected void addAdditionalLinks( ResponseBuilder builder )
		{
		}

		protected abstract void addCacheControl( ResponseBuilder responseBuilder );

		protected abstract boolean isPreconditionMet( Request request, T resultFromDatabase );
	}
}
