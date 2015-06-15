package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.usermanagement.models.User;
import de.fhws.applab.restserverspi.database.dao.AbstractLoadSingleByQueryOperation;

import java.sql.ResultSet;
import java.sql.SQLException;

import static de.fhws.applab.usermanagement.database.tables.UserTable.*;

/**
 * Created by braunpet on 14.06.15.
 */
public class LoadSingleUserByEmailAndPassword extends AbstractLoadSingleByQueryOperation<QueryByEmailAndPassword,User>
{
	public LoadSingleUserByEmailAndPassword( )
	{
	}

	@Override protected String createSQLStatement( )
	{
		final String email = this.params.getEmailAddress();
		final String password = this.params.getPassword();

		return  "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = '" + email + "' AND " + COL_PASSWORD + " = SHA('" + password + "')";
	}

	@Override protected User createModel( ResultSet resultSet ) throws SQLException
	{
		return DatabaseUserModel.createUser( resultSet );
	}
}
