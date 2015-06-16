package de.fhws.applab.restserverspi.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractLoadSingleOperation<P,R> extends AbstractLoadOperation<P,R>
{
	public AbstractLoadSingleOperation( IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected R processResultSet( ResultSet resultSet ) throws SQLException
	{
		if ( resultSet.next( ) )
		{
			return createModel( resultSet );
		}

		return null;
	}

	protected abstract R createModel( ResultSet resultSet ) throws SQLException;
}
