package de.fhws.applab.restserverspi.api.filters;

import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.IOException;

//@Provider
//@UserAuthorization
public abstract class AbstractUserAuthorizationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException
	{
		final String authHeader = requestContext.getHeaderString( HttpHeaders.AUTHORIZATION );

		if (authHeader == null)
		{
			requestContext
					.abortWith( Response.status( Status.UNAUTHORIZED )
						.header( HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"No Permission\"" )
						.entity( "Page requires login." )
						.build( ) );
		}
		else
		{
			final String withoutBasic = authHeader.replaceFirst("[Bb]asic ", "");
			final String userColonPass = Base64.decodeAsString( withoutBasic );
			final String[] asArray = userColonPass.split( ":" );

			Object user = verifyUserCredentials( asArray[ 0 ], asArray[ 1 ] );

			if (user == null)
			{
				requestContext
					.abortWith( Response.status( Status.UNAUTHORIZED )
						.header( HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\" \n Not authenticated\"" )
						.entity( "Page requires login with correct authentication information." )
						.build( ) );
			}
			else
			{
				addUserToRequestContext( requestContext, user );
			}
		}
	}

	protected abstract Object verifyUserCredentials( String userName, String password );

	protected abstract void addUserToRequestContext( ContainerRequestContext requestContext, Object user );
}
