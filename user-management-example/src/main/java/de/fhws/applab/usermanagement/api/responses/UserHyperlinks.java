/*
 * Copyright (c) peter.braun@fhws.de
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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

	public static void addLinkWithRelType( UriInfo uriInfo, Response.ResponseBuilder builder, String relType,
		long userId )
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
		URI getAllUsersUri = builder.build( );
		response.link( getAllUsersUri, UserRelationTypes.REL_TYPE_GET_ALL_USERS );
	}

}
