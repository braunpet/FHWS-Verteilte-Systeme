package de.fhws.applab.usermanagement.api.responses;

import de.fhws.applab.restserverspi.api.responses.AbstractPostResponse;
import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.usermanagement.database.DataAccessObjectsFactory;
import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.models.User;

import javax.ws.rs.core.UriInfo;

/**
 * Created by braunpet on 14.06.15.
 */
public class CreateNewUserResponse extends AbstractPostResponse
{
	public static CreateNewUserResponseBuilder newBuilder( UriInfo uriInfo )
	{
		return new CreateNewUserResponseBuilder( uriInfo );
	}

	public static class CreateNewUserResponseBuilder extends AbstractPostResponseBuilder<User>
	{
		public CreateNewUserResponseBuilder( UriInfo uriInfo )
		{
			super( uriInfo );
		}

		@Override protected void saveModelToDatabase( User newModel ) throws DatabaseException
		{
			UserDAO userDAO = DataAccessObjectsFactory.getInstance( ).createUserDAO( );
			newModel.setLastModifiedAt( System.currentTimeMillis() );
			userDAO.saveUser( newModel );
		}
	}
}
