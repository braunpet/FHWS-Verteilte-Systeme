package de.fhws.applab.restserverspi.api.responses;

import de.fhws.applab.restserverspi.database.DatabaseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractGetResponse extends AbstractResponse
{
	protected AbstractGetResponse( )
	{}

	public abstract static class SingleResponseBuilder<T> extends AbstractResponse.AbstractResponseBuilder
	{
		protected T result;

		protected SingleResponseBuilder( UriInfo uriInfo )
		{
			super( uriInfo );
		}

		public final Response build( )
		{
			try
			{
				this.result = loadFromDatabase( );

				if( this.result != null )
				{
					ResponseBuilder builder = Response.ok( this.result );

					addCacheControl( builder );
					addAdditionalLinks( builder );

					return builder.build( );
				}
				else
				{
					return Response.status( Response.Status.NOT_FOUND ).build();
				}
			}
			catch( Exception e )
			{
				e.printStackTrace();
				return Response.serverError().build();
			}
		}

		protected abstract T loadFromDatabase() throws DatabaseException;

		protected void addAdditionalLinks( ResponseBuilder builder )
		{}

		protected abstract void addCacheControl( ResponseBuilder responseBuilder );
	}
}
