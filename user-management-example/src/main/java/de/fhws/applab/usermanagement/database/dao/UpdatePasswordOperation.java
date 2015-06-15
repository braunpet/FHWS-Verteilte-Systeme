package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.usermanagement.database.tables.UserTable;
import de.fhws.applab.usermanagement.models.User;
import de.fhws.applab.restserverspi.database.dao.AbstractUpdateOperation;

import static de.fhws.applab.usermanagement.database.tables.UserTable.COL_ID;
import static de.fhws.applab.usermanagement.database.tables.UserTable.COL_PASSWORD;

/**
 * Created by braunpet on 14.06.15.
 */
public class UpdatePasswordOperation extends AbstractUpdateOperation<User>
{
	public UpdatePasswordOperation( )
	{
	}

	@Override protected String createSQLStatement( )
	{
		final StringBuffer sqlStmt = new StringBuffer( );

		sqlStmt.append( "UPDATE " ).append( UserTable.TABLE_NAME );
		sqlStmt.append( " SET " );
		sqlStmt.append( COL_PASSWORD + " = " ).append( "SHA('" + params.getPassword( ) + "')" );
		sqlStmt.append( " WHERE " + COL_ID + " = " ).append( params.getId( ) );

		return sqlStmt.toString();
	}
}
