package de.fhws.applab.restserverspi.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractLoadOperation<P,R> extends AbstractDatabaseOperation<P,R>
{
	public AbstractLoadOperation( )
	{
	}

	@Override protected ResultSet executeSQLStatement( Statement sqlStatement, String sql ) throws SQLException
	{
		return sqlStatement.executeQuery( sql );
	}


}
