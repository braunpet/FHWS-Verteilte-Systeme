package de.fhws.applab.usermanagement.database.tables;

import de.fhws.applab.restserverspi.database.tables.AbstractTable;

import java.sql.Connection;
import java.sql.Statement;

public class UserTable extends AbstractTable
{
	public static final String TABLE_NAME = "User";

	public static final String COL_EMAIL = "emailAddress";

	public static final String COL_FIRSTNAME = "firstName";

	public static final String COL_LASTNAME = "lastName";

	public static final String COL_PASSWORD = "password";

	public static final String COL_LASTMODIFIED = "lastModified";


	@Override
	protected String getTableName( )
	{
		return TABLE_NAME;
	}

	@Override
	public void createTable( Connection con )
	{
		createUserTable( con );
		createFirstUser( con );
	}

	protected void createUserTable( Connection con )
	{
		try
		{
			final StringBuffer sb = new StringBuffer( );

			sb.append( "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" );
			sb.append( COL_ID + " bigint unsigned NOT NULL AUTO_INCREMENT," );
			sb.append( COL_EMAIL + " varchar(255) NOT NULL UNIQUE," );
			sb.append( COL_PASSWORD + " varchar(255) NOT NULL," );
			sb.append( COL_FIRSTNAME + " varchar(255) NOT NULL default ''," );
			sb.append( COL_LASTNAME + " varchar(255) NOT NULL default ''," );
			sb.append( COL_LASTMODIFIED + " bigint unsigned NOT NULL default '0', ");
			sb.append( "PRIMARY KEY (`" + COL_ID + "`), " );
			sb.append( "INDEX theIdx (" + COL_EMAIL + ")" );
			sb.append( " ) " );
			sb.append( "ENGINE=InnoDB DEFAULT CHARSET=latin1" );

			final Statement stmt = con.createStatement( );
			stmt.executeUpdate( sb.toString( ) );

		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
	}

	protected void createFirstUser( Connection con )
	{
		try
		{
			final StringBuffer sb = new StringBuffer( );

			sb.append( "INSERT INTO " + TABLE_NAME + " (" + COL_EMAIL + "," + COL_PASSWORD + ") VALUES (" );
			sb.append( "'peter.braun@fhws.de', " );
			sb.append( "SHA('testing')" );
			sb.append( ")" );

			final Statement stmt = con.createStatement( );
			stmt.executeUpdate( sb.toString( ) );

		}
		catch ( Exception e )
		{
			// an exception is thrown if this user already exists, which is normally the case, so we ignore the exception
		}
	}
}
