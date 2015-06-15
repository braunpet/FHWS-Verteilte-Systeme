package de.fhws.applab.restserverspi.database.tables;

import java.sql.Connection;
import java.sql.Statement;

public abstract class AbstractTable
{
	public static final String COL_ID = "id";


	protected abstract String getTableName( );

	public final void prepareTable( boolean delete, Connection con ) throws Exception
	{
		if ( delete )
		{
			deleteTable( con );
		}

		createTable( con );
	}

	protected abstract void createTable( Connection con );

	protected final void deleteTable( Connection con ) throws Exception
	{
		final StringBuffer sb = new StringBuffer( );
		sb.append( "DROP TABLE IF EXISTS " );
		sb.append( getTableName( ) );
		sb.append( ";" );

		final Statement stmt = con.createStatement( );
		stmt.executeUpdate( sb.toString( ) );
	}

}
