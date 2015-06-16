package de.fhws.applab.restserverspi.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.restserverspi.models.AbstractModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractSaveOperation<P extends AbstractModel> extends AbstractDatabaseOperation<P,Void>
{
	public AbstractSaveOperation( IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected ResultSet executeSQLStatement( Statement sqlStatement, String sql ) throws SQLException
	{
		sqlStatement.executeUpdate( sql, Statement.RETURN_GENERATED_KEYS );
		return sqlStatement.getGeneratedKeys( );
	}

	@Override protected Void processResultSet( ResultSet resultSet ) throws SQLException
	{
		resultSet.next( );
		params.setId( resultSet.getLong( 1 ) );
		return null;
	}
}
