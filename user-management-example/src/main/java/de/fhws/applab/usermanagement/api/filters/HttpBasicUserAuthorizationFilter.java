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
		catch ( Exception e )
		{
			e.printStackTrace( );
			return null;
		}
	}

	@Override protected void addUserToRequestContext( ContainerRequestContext requestContext, Object user )
	{
		requestContext.setProperty( PROP_USER_OBJECT, user );
	}
}
