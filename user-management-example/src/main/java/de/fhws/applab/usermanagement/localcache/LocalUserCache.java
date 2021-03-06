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

package de.fhws.applab.usermanagement.localcache;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.localcache.AbstractLocalCache;
import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.models.User;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by braunpet on 19.06.15.
 */
public class LocalUserCache extends AbstractLocalCache<User> implements UserDAO
{
	private UserDAO database;

	public LocalUserCache( long maxCacheSize, UserDAO database )
	{
		super( maxCacheSize );
		this.database = database;
	}

	@Override public void saveUser( User user ) throws DatabaseException
	{
		this.database.saveUser( user );
	}

	@Override synchronized public void updateUser( User user ) throws DatabaseException
	{
		this.removeFromCache( user.getId( ) );
		this.database.updateUser( user );
	}

	@Override synchronized public User loadUser( final long userId ) throws DatabaseException
	{
		return this.getFromCache( userId, new Callable<User>( )
		{
			@Override public User call( ) throws Exception
			{
				User returnValue = LocalUserCache.this.database.loadUser( userId );
				if ( returnValue == null )
				{
					throw new ExecutionException( "requested user not found", null );
				}
				else
				{
					return returnValue;
				}
			}
		} );
	}

	@Override public Collection<User> loadAllUsers( ) throws DatabaseException
	{
		return this.database.loadAllUsers( );
	}

	@Override synchronized public void deleteUser( long userId ) throws DatabaseException
	{
		this.removeFromCache( userId );
		this.database.deleteUser( userId );
	}

	@Override public User loadByEmail( String email, String password ) throws DatabaseException
	{
		return this.database.loadByEmail( email, password );
	}

	@Override public long getNumberOfUsers( ) throws DatabaseException
	{
		return this.database.getNumberOfUsers( );
	}

	@Override public void updatePassword( User user ) throws DatabaseException
	{

	}

	@Override public void shutdown( )
	{

	}
}
