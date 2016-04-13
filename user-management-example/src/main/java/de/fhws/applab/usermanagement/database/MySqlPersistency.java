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

package de.fhws.applab.usermanagement.database;

import de.fhws.applab.restserverspi.database.AbstractMySqlPersistency;
import de.fhws.applab.restserverspi.database.config.MySqlConfig;
import de.fhws.applab.restserverspi.database.tables.AbstractTable;
import de.fhws.applab.usermanagement.database.tables.UserTable;

import java.util.HashSet;
import java.util.Set;

public class MySqlPersistency extends AbstractMySqlPersistency
{
	private static String DATABASE_NAME = "usermanagement";

	public static final MySqlPersistency createPersistencyFor( MySqlConfig config )
	{
		return new MySqlPersistency( config );
	}

	public MySqlPersistency( MySqlConfig configuration )
	{
		this( configuration, false );
	}

	public MySqlPersistency( MySqlConfig configuration, final boolean deleteDatabase )
	{
		super( configuration, deleteDatabase );
	}

	@Override public String getDatabaseName( )
	{
		return DATABASE_NAME;
	}

	@Override protected Set<AbstractTable> getAllTables( )
	{
		Set<AbstractTable> returnValue = new HashSet<>( );
		returnValue.add( new UserTable( ) );
		return returnValue;
	}
}
