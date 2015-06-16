package de.fhws.applab.restserverspi.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by braunpet on 14.06.15.
 */
public class AbstractGetTotalSizeOfTable extends AbstractDatabaseOperation<String,Long>
{
	public AbstractGetTotalSizeOfTable( IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected Long processResultSet( ResultSet resultSet ) throws SQLException
	{
		if ( resultSet.next( ) )
		{
			return resultSet.getLong( 1 );
		}

		return Long.valueOf( -1 );
	}

	@Override protected ResultSet executeSQLStatement( Statement sqlStatement, String sql ) throws SQLException
	{
		return sqlStatement.executeQuery( sql );
	}

	@Override protected String createSQLStatement( )
	{
		return "SELECT count(*) FROM " + this.params;
	}
}
