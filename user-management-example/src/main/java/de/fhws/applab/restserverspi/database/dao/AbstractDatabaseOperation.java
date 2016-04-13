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

package de.fhws.applab.restserverspi.database.dao;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.database.IPersistency;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractDatabaseOperation<P, R>
{
	protected final IPersistency mysql;

	protected Connection databaseConnection;

	protected P params;

	protected AbstractDatabaseOperation( IPersistency persistency )
	{
		this.mysql = persistency;
	}

	public final R execute( P param ) throws DatabaseException
	{
		this.params = param;

		try
		{
			return _process( );
		}
		catch ( SQLException e )
		{
			handleExceptions( e );
			return null;
		}
		finally
		{
			closeConnection( );
		}
	}

	private R _process( ) throws SQLException
	{
		this.databaseConnection = this.mysql.getConnection( );

		final Statement stmt = this.databaseConnection.createStatement( );

		final String sqlStmt = createSQLStatement( );

		final ResultSet resultSet = executeSQLStatement( stmt, sqlStmt );

		final R returnValue = processResultSet( resultSet );

		closeResultSet( resultSet );
		stmt.close( );

		return returnValue;
	}

	private void closeResultSet( ResultSet resultSet ) throws SQLException
	{
		if ( resultSet != null )
		{
			resultSet.close( );
		}
	}

	private void handleExceptions( SQLException e ) throws DatabaseException
	{
		if ( this.databaseConnection != null )
		{
			try
			{
				this.databaseConnection.rollback( );
			}
			catch ( Exception ee )
			{
				ee.printStackTrace( );
			}
		}
		throw new DatabaseException( "Problem while database operation", e );
	}

	private void closeConnection( )
	{
		if ( this.databaseConnection != null )
		{
			try
			{
				this.databaseConnection.close( );
			}
			catch ( Exception ee )
			{
				ee.printStackTrace( );
			}
		}
	}

	protected abstract String createSQLStatement( );

	protected abstract ResultSet executeSQLStatement( Statement sqlStatement, String sql ) throws SQLException;

	protected abstract R processResultSet( ResultSet resultSet ) throws SQLException;

}
