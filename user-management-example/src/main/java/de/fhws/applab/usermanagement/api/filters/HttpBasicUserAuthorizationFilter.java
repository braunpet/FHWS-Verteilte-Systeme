package de.fhws.applab.usermanagement.api.filters;

import de.fhws.applab.restserverspi.api.annotations.UserAuthorization;
import de.fhws.applab.restserverspi.api.filters.AbstractUserAuthorizationFilter;
import de.fhws.applab.usermanagement.database.DataAccessObjectsFactory;
import de.fhws.applab.usermanagement.database.dao.UserDAO;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;

/**
 * Created by braunpet on 14.06.15.
 */
@Provider
@UserAuthorization
public class HttpBasicUserAuthorizationFilter extends AbstractUserAuthorizationFilter
{
	public static final String PROP_USER_OBJECT = "userObject";

	@Override protected Object verifyUserCredentials( String userName, String password )
	{
		try
		{
			UserDAO userDAO = DataAccessObjectsFactory.getInstance( ).createUserDAO( );
			return userDAO.loadByEmail( userName, password );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override protected void addUserToRequestContext( ContainerRequestContext requestContext, Object user )
	{
		requestContext.setProperty( PROP_USER_OBJECT, user );
	}
}
