package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.usermanagement.database.tables.UserTable;
import de.fhws.applab.usermanagement.models.User;
import de.fhws.applab.restserverspi.database.dao.AbstractLoadByIdOperation;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by braunpet on 14.06.15.
 */
public class LoadSingleUserByIdOperation extends AbstractLoadByIdOperation<User>
{
	public LoadSingleUserByIdOperation( IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected String createSQLStatement( )
	{
		return "SELECT * FROM " + UserTable.TABLE_NAME + " WHERE id = " + this.params.toString();
	}

	@Override protected User createModel( ResultSet resultSet ) throws SQLException
	{
		return DatabaseUserModel.createUser( resultSet );
	}
}
