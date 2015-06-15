package de.fhws.applab.usermanagement.api.responses;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Created by braunpet on 14.06.15.
 */
public class UserHyperlinks
{
	private static final String URI_TO_USER = "um/api/users/{userId}";

	private static final String URI_TO_ALL_USERS = "um/api/users";

	public static void addLinkWithRelType( UriInfo uriInfo, Response.ResponseBuilder builder, String relType, long userId )
	{
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder( );
		uriBuilder.replacePath( URI_TO_USER );
		URI uri = uriBuilder.build( userId );
		builder.link( uri, relType );
	}

	public static void addLinkToAllUsers( UriInfo uriInfo, Response.ResponseBuilder response )
	{
		UriBuilder builder = uriInfo.getAbsolutePathBuilder( );
		builder.replacePath( URI_TO_ALL_USERS );
		URI getAllUsersUri = builder.build(  );
		response.link( getAllUsersUri, UserRelationTypes.REL_TYPE_GET_ALL_USERS );
	}

}
