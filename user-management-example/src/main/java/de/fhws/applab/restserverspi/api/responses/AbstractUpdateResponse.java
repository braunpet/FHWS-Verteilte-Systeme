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

	public abstract static class AbstractUpdateResponseBuilder<T extends AbstractModel> extends AbstractResponse.AbstractResponseBuilder
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
				if( isUpdateAllowed() )
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
			catch( Exception e )
			{
				return Response.status( Response.Status.NOT_FOUND ).build( );
			}
		}

		protected abstract void addSelfLink( Response.ResponseBuilder builder );

		protected abstract void addLinkToCollection( Response.ResponseBuilder responseBuilder );

		protected abstract void updateDatabase( ) throws DatabaseException;

		protected abstract boolean isUpdateAllowed() throws DatabaseException;
	}

}
