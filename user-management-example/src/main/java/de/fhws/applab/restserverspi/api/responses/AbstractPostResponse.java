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
	{}

	public static abstract class AbstractPostResponseBuilder<T extends AbstractModel> extends AbstractResponse.AbstractResponseBuilder
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
			if( createNewModel( this.modelToBeCreated ) )
			{
				return Response.created( createdURI( ) ).build( );
			}
			else
			{
				return Response.serverError().build();
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
			catch( Exception e )
			{
				e.printStackTrace();
				return false;
			}
		}

		private URI createdURI( )
		{
			UriBuilder builder = uriInfo.getAbsolutePathBuilder( );
			return builder.path( Long.toString(this.modelToBeCreated.getId()) ).build( );
		}
	}
}
