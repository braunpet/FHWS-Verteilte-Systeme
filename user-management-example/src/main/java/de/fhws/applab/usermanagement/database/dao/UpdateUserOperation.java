package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.usermanagement.database.tables.UserTable;
import de.fhws.applab.usermanagement.models.User;
import de.fhws.applab.restserverspi.database.dao.AbstractUpdateOperation;

import static de.fhws.applab.usermanagement.database.tables.UserTable.*;

/**
 * Created by braunpet on 14.06.15.
 */
public class UpdateUserOperation extends AbstractUpdateOperation<User>
{
	public UpdateUserOperation( )
	{
	}

	@Override protected String createSQLStatement( )
	{
		final StringBuffer sqlStmt = new StringBuffer( );

		sqlStmt.append( "UPDATE " ).append( UserTable.TABLE_NAME );
		sqlStmt.append( " SET " );
		sqlStmt.append( COL_FIRSTNAME + " = " ).append( "'" + params.getFirstName( ) + "'" ).append( "," );
		sqlStmt.append( COL_LASTNAME + " = " ).append( "'" + params.getLastName( ) + "'" );
		sqlStmt.append( " WHERE " + COL_ID + " = " ).append( params.getId( ) );

		return sqlStmt.toString();
	}
}
