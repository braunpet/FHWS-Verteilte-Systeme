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

package de.fhws.applab.restserverspi.database.tables;

import java.sql.Connection;
import java.sql.Statement;

public abstract class AbstractTable
{
	public static final String COL_ID = "id";

	protected abstract String getTableName( );

	public final void prepareTable( boolean delete, Connection con ) throws Exception
	{
		if ( delete )
		{
			deleteTable( con );
		}

		createTable( con );
	}

	protected abstract void createTable( Connection con );

	protected final void deleteTable( Connection con ) throws Exception
	{
		final StringBuffer sb = new StringBuffer( );
		sb.append( "DROP TABLE IF EXISTS " );
		sb.append( getTableName( ) );
		sb.append( ";" );

		final Statement stmt = con.createStatement( );
		stmt.executeUpdate( sb.toString( ) );
	}

}
