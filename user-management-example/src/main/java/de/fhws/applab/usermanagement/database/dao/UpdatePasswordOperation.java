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

import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.restserverspi.database.dao.AbstractUpdateOperation;
import de.fhws.applab.usermanagement.database.tables.UserTable;
import de.fhws.applab.usermanagement.models.User;

import static de.fhws.applab.usermanagement.database.tables.UserTable.COL_ID;
import static de.fhws.applab.usermanagement.database.tables.UserTable.COL_PASSWORD;

/**
 * Created by braunpet on 14.06.15.
 */
public class UpdatePasswordOperation extends AbstractUpdateOperation<User>
{
	public UpdatePasswordOperation( IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected String createSQLStatement( )
	{
		final StringBuffer sqlStmt = new StringBuffer( );

		sqlStmt.append( "UPDATE " ).append( UserTable.TABLE_NAME );
		sqlStmt.append( " SET " );
		sqlStmt.append( COL_PASSWORD + " = " ).append( "SHA('" + params.getPassword( ) + "')" );
		sqlStmt.append( " WHERE " + COL_ID + " = " ).append( params.getId( ) );

		return sqlStmt.toString( );
	}
}
