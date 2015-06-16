package de.fhws.applab.restserverspi.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractLoadCollectionOperation<P,R> extends AbstractLoadOperation<P,Collection<R>>
{
	public AbstractLoadCollectionOperation( IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected Collection<R> processResultSet( ResultSet resultSet ) throws SQLException
	{
		final Collection<R> returnValue = new LinkedList<R>();

		while ( resultSet.next( ) )
		{
			returnValue.add( createModel( resultSet ) );
		}

		return returnValue;
	}

	protected abstract R createModel( ResultSet resultSet ) throws SQLException;
}
