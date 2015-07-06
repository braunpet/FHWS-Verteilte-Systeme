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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

public abstract class AbstractPostResponse extends AbstractResponse
{
	protected AbstractPostResponse( )
	{
	}

	public static abstract class AbstractPostResponseBuilder<T extends AbstractModel>
		extends AbstractResponse.AbstractResponseBuilder
	{
		protected T modelToBeCreated;

		public AbstractPostResponseBuilder( UriInfo uriInfo )
		{
			super( uriInfo );
		}

		public final AbstractPostResponseBuilder<T> withNewModel( T newModel )
		{
			this.modelToBeCreated = newModel;
			return this;
		}

		@Override
		public final Response build( )
		{
			if ( createNewModel( this.modelToBeCreated ) )
			{
				return Response.created( createdURI( ) ).build( );
			}
			else
			{
				return Response.serverError( ).build( );
			}
		}

		protected abstract void saveModelToDatabase( T newModel ) throws DatabaseException;

		private boolean createNewModel( T newModel )
		{
			try
			{
				saveModelToDatabase( this.modelToBeCreated );
				return true;
			}
			catch ( Exception e )
			{
				e.printStackTrace( );
				return false;
			}
		}

		private URI createdURI( )
		{
			UriBuilder builder = uriInfo.getAbsolutePathBuilder( );
			return builder.path( Long.toString( this.modelToBeCreated.getId( ) ) ).build( );
		}
	}
}
