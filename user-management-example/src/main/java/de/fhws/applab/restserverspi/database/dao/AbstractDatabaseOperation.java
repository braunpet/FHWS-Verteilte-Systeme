package de.fhws.applab.restserverspi.database.dao;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.usermanagement.database.MySqlPersistency;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractDatabaseOperation<P,R>
{
	protected final MySqlPersistency mysql;

	protected Connection databaseConnection;

	protected P params;

	protected AbstractDatabaseOperation()
	{
		this.mysql = MySqlPersistency.getInstance();
	}

	public final R execute( P param ) throws DatabaseException
	{
		this.params = param;

		try
		{
			return _process();
		}
		catch ( SQLException e )
		{
			handleExceptions( e );
			return null;
		}
		finally
		{
			closeConnection();
		}
	}

	private R _process() throws SQLException
	{
		this.databaseConnection = this.mysql.getConnection( );

		final Statement stmt = this.databaseConnection.createStatement( );

		final String sqlStmt = createSQLStatement();

		final ResultSet resultSet = executeSQLStatement( stmt, sqlStmt );

		final R returnValue = processResultSet( resultSet );

		closeResultSet( resultSet );
		stmt.close( );

		return returnValue;
	}

	private void closeResultSet( ResultSet resultSet ) throws SQLException
	{
		if( resultSet != null )
		{
			resultSet.close();
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

	private void closeConnection()
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
