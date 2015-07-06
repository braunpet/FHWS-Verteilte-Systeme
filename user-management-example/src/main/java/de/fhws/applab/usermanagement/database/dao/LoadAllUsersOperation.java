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
import de.fhws.applab.restserverspi.database.dao.AbstractLoadCollectionOperation;
import de.fhws.applab.usermanagement.database.tables.UserTable;
import de.fhws.applab.usermanagement.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by braunpet on 14.06.15.
 */
public class LoadAllUsersOperation extends AbstractLoadCollectionOperation<Void, User>
{
	public LoadAllUsersOperation( IPersistency persistency )
	{
		super( persistency );
	}

	public Collection<User> execute( ) throws DatabaseException
	{
		return this.execute( null );
	}

	@Override protected String createSQLStatement( )
	{
		return "SELECT * FROM " + UserTable.TABLE_NAME;
	}

	@Override protected User createModel( ResultSet resultSet ) throws SQLException
	{
		return DatabaseUserModel.createUser( resultSet );
	}
}
