package de.fhws.applab.restserverspi.api.responses;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.models.AbstractModel;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGetCollectionResponse extends AbstractResponse
{
	public static final String QUERY_PARAM_SIZE = "size";

	public static final String QUERY_PARAM_SIZE_AS_TEMPLATE = "{" + QUERY_PARAM_SIZE + "}";

	public static final String QUERY_PARAM_OFFSET = "offset";

	public static final String QUERY_PARAM_OFFSET_AS_TEMPLATE = "{" + QUERY_PARAM_OFFSET + "}";

	public static final String HEADER_TOTALNUMBEROFRESULTS = "X-totalnumberofresults";

	protected AbstractGetCollectionResponse( )
	{}

	public abstract static class CollectionResponseBuilder<T extends AbstractModel> extends AbstractResponse.AbstractResponseBuilder
	{
		private List<T> result;

		protected int offsetFromRequest;

		protected int sizeFromRequest;

		protected int totalNumberOfResults;

		protected CollectionResponseBuilder( UriInfo uriInfo )
		{
			super( uriInfo );
		}

		protected abstract Collection<T> loadFromDatabase() throws DatabaseException;

		public final CollectionResponseBuilder<T> requestedOffsetWas( int offset )
		{
			this.offsetFromRequest = Math.max( 0, offset );
			return this;
		}

		public final CollectionResponseBuilder<T> requestedSizeWas( int size )
		{
			this.sizeFromRequest = Math.max( 1, size );
			return this;
		}

		@Override
		public final Response build( )
		{
			try
			{
				this.result = new LinkedList<T>( loadFromDatabase() );
				this.totalNumberOfResults = this.result.size( );

				ResponseBuilder builder = Response.ok( Pager.page( this.result, this.offsetFromRequest, this.sizeFromRequest ) );

				addCacheControl( builder );
				addSelfLink( builder );
				addPrevLink( builder );
				addNextLink( builder );
				addTotalNumberOfResults( builder );

				_build( builder );

				return builder.build( );
			}
			catch( Exception e )
			{
				e.printStackTrace();
				return Response.serverError().build();
			}
		}

		private void addCacheControl( ResponseBuilder responseBuilder )
		{
			CacheControl cacheControl = new CacheControl();
			cacheControl.setNoCache( true );
			cacheControl.setNoStore( true );
			responseBuilder.cacheControl( cacheControl );
		}

		protected void _build( ResponseBuilder builder )
		{}

		private void addSelfLink( ResponseBuilder builder )
		{
			builder.link( getSelfUri( uriInfo ), RelationTypes.REL_TYPE_SELF );
		}


		private void addPrevLink( ResponseBuilder builder )
		{
			if ( hasPrevLink( ) )
			{
				builder.link( getPrevUri( uriInfo ), RelationTypes.REL_TYPE_PREV_PAGE );
			}
		}

		private void addNextLink( ResponseBuilder builder )
		{
			if ( hasNextLink( ) )
			{
				builder.link( getNextUri( uriInfo ), RelationTypes.REL_TYPE_NEXT_PAGE );
			}
		}

		private void addTotalNumberOfResults( ResponseBuilder builder )
		{
			builder.header( HEADER_TOTALNUMBEROFRESULTS, Integer.toString( this.totalNumberOfResults ) );
		}

		private boolean hasPrevLink( )
		{
			return this.offsetFromRequest > 0;
		}

		private boolean hasNextLink( )
		{
			return this.offsetFromRequest + this.sizeFromRequest < this.totalNumberOfResults;
		}

		private URI getSelfUri( UriInfo uriInfo )
		{
			UriBuilder uriBuilder = createUriBuilder( uriInfo );

			return uriBuilder.build( this.offsetFromRequest, this.sizeFromRequest );
		}


		private URI getPrevUri( UriInfo uriInfo )
		{
			UriBuilder uriBuilder = createUriBuilder( uriInfo );

			int newOffset = Math.max( this.offsetFromRequest - sizeFromRequest, 0 );
			int newSize = Math.min( this.sizeFromRequest, this.offsetFromRequest );

			return uriBuilder.build( newOffset, newSize );
		}

		private URI getNextUri( UriInfo uriInfo )
		{
			UriBuilder uriBuilder = createUriBuilder( uriInfo );

			int newOffset = Math.min( this.offsetFromRequest + sizeFromRequest, this.totalNumberOfResults - 1 );
			int newSize = Math.min( this.sizeFromRequest, this.totalNumberOfResults - newOffset );

			return uriBuilder.build( newOffset, newSize );
		}

		private UriBuilder createUriBuilder( UriInfo uriInfo )
		{
			return uriInfo.getAbsolutePathBuilder( )
				.queryParam( QUERY_PARAM_OFFSET, QUERY_PARAM_OFFSET_AS_TEMPLATE )
				.queryParam( QUERY_PARAM_SIZE, QUERY_PARAM_SIZE_AS_TEMPLATE );
		}
	}

}
