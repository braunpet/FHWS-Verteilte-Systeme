package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.usermanagement.database.tables.UserTable;
import de.fhws.applab.usermanagement.models.User;
import de.fhws.applab.restserverspi.database.dao.AbstractLoadCollectionOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by braunpet on 14.06.15.
 */
public class LoadAllUsersOperation extends AbstractLoadCollectionOperation<Void,User>
{
	public LoadAllUsersOperation( IPersistency persistency )
	{
		super( persistency );
	}

	public Collection<User> execute() throws DatabaseException
	{
		return this.execute( null );
	}

	@Override protected String createSQLStatement( )
	{
		return "SELECT * FROM " + UserTable.TABLE_NAME;
	}

	@Override protected User createModel( ResultSet resultSet ) throws SQLException
	{
		return DatabaseUserModel.createUser( resultSet );
	}
}
