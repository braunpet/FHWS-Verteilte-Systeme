package de.fhws.applab.usermanagement.api.responses;

import de.fhws.applab.restserverspi.api.responses.AbstractGetCollectionResponse;
import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.usermanagement.database.DataAccessObjectsFactory;
import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.models.User;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

/**
 * Created by braunpet on 14.06.15.
 */
public class GetAllUsersResponse extends AbstractGetCollectionResponse
{
	public static final GetAllUsersResponseBuilder newBuilder( UriInfo uriInfo )
	{
		return new GetAllUsersResponseBuilder( uriInfo );
	}

	public static class GetAllUsersResponseBuilder extends CollectionResponseBuilder<User>
	{
		public GetAllUsersResponseBuilder( UriInfo uriInfo )
		{
			super( uriInfo );
		}

		@Override protected Collection<User> loadFromDatabase( ) throws DatabaseException
		{
			UserDAO userDAO = DataAccessObjectsFactory.getInstance().createUserDAO();
			return userDAO.loadAllUsers();
		}

		@Override protected void _build( Response.ResponseBuilder builder )
		{
			addCreateLink( builder );
		}

		private void addCreateLink( Response.ResponseBuilder builder )
		{
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder( );
			URI uri = uriBuilder.build( );
			builder.link( uri, UserRelationTypes.REL_TYPE_CREATE_USER );
		}

	}
}
