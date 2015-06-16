package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.usermanagement.database.tables.UserTable;
import de.fhws.applab.usermanagement.models.User;
import de.fhws.applab.restserverspi.database.dao.AbstractSaveOperation;

import static de.fhws.applab.usermanagement.database.tables.UserTable.*;

/**
 * Created by braunpet on 14.06.15.
 */
public class SaveUserOperation extends AbstractSaveOperation<User>
{
	public SaveUserOperation( IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected String createSQLStatement( )
	{
		final StringBuffer sqlStmt = new StringBuffer( );

		sqlStmt.append( "INSERT INTO " + UserTable.TABLE_NAME );
		sqlStmt.append( " (" + COL_EMAIL + "," + COL_PASSWORD + "," + COL_LASTMODIFIED + "," + COL_FIRSTNAME + "," + COL_LASTNAME + ") VALUES( " );
		sqlStmt.append( "'" + params.getEmailAddress( ) + "'" ).append( "," );
		sqlStmt.append( "SHA('" + params.getPassword( ) + "')" ).append( "," );
		sqlStmt.append( "'" + params.getLastModifiedAt() + "'" ).append( "," );
		sqlStmt.append( "'" + params.getFirstName( ) + "'" ).append( "," );
		sqlStmt.append( "'" + params.getLastName( ) + "'" );
		sqlStmt.append( ")" );

		return sqlStmt.toString();
	}
}
