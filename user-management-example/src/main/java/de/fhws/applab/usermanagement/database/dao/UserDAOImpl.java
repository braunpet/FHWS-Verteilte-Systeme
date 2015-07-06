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

package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.usermanagement.database.MySqlPersistency;
import de.fhws.applab.usermanagement.database.config.MySqlConfigDispatcher;
import de.fhws.applab.usermanagement.models.User;

import java.util.Collection;

public class UserDAOImpl implements UserDAO
{
	private IPersistency persistency;

	public UserDAOImpl( )
	{
		this.persistency = new MySqlPersistency( MySqlConfigDispatcher.getMySqlConfig( ) );
	}

	@Override
	public void saveUser( User user ) throws DatabaseException
	{
		new SaveUserOperation( this.persistency ).execute( user );
	}

	@Override
	public void updateUser( User user ) throws DatabaseException
	{
		new UpdateUserOperation( this.persistency ).execute( user );
	}

	@Override
	public void updatePassword( User user ) throws DatabaseException
	{
		new UpdatePasswordOperation( this.persistency ).execute( user );
	}

	@Override
	public User loadUser( long userId ) throws DatabaseException
	{
		return new LoadSingleUserByIdOperation( this.persistency ).execute( userId );
	}

	@Override
	public Collection<User> loadAllUsers( ) throws DatabaseException
	{
		return new LoadAllUsersOperation( this.persistency ).execute( );
	}

	@Override
	public void deleteUser( long userId ) throws DatabaseException
	{
		new DeleteUserOperation( this.persistency ).execute( userId );
	}

	@Override
	public User loadByEmail( String email, String password ) throws DatabaseException
	{
		return new LoadSingleUserByEmailAndPassword( this.persistency )
			.execute( new QueryByEmailAndPassword( email, password ) );
	}

	@Override
	public long getNumberOfUsers( ) throws DatabaseException
	{
		return new GetNumberOfUsers( this.persistency ).execute( );
	}

	@Override public void shutdown( )
	{
		this.persistency.shutdown( );
	}
}
