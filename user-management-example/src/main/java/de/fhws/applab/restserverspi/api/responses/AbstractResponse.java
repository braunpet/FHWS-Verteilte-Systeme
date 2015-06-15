package de.fhws.applab.restserverspi.api.responses;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class AbstractResponse
{
	protected AbstractResponse( )
	{}

	public abstract static class AbstractResponseBuilder
	{
		protected UriInfo uriInfo;

		protected AbstractResponseBuilder( UriInfo uriInfo )
		{
			this.uriInfo = uriInfo;
		}

		public abstract Response build( );

	}

}
