package de.fhws.applab.restserverspi.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractDeleteOperation extends AbstractDatabaseOperation<Long,Void>
{
	public AbstractDeleteOperation( IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected ResultSet executeSQLStatement( Statement sqlStatement, String sql ) throws SQLException
	{
		sqlStatement.executeUpdate( sql );
		return null;
	}

	@Override protected Void processResultSet( ResultSet resultSet ) throws SQLException
	{
		return null;
	}
}
