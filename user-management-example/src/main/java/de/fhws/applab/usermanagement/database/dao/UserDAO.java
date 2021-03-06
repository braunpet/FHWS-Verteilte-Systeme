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
import de.fhws.applab.usermanagement.models.User;

import java.util.Collection;

public interface UserDAO
{
	void saveUser( User user ) throws DatabaseException;

	void updateUser( User user ) throws DatabaseException;

	User loadUser( long userId ) throws DatabaseException;

	Collection<User> loadAllUsers( ) throws DatabaseException;

	void deleteUser( long userId ) throws DatabaseException;

	User loadByEmail( String email, String password ) throws DatabaseException;

	long getNumberOfUsers( ) throws DatabaseException;

	void updatePassword( User user ) throws DatabaseException;

	void shutdown( );
}
