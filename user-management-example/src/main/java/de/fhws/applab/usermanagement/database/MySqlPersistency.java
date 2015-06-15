package de.fhws.applab.usermanagement.database;

import de.fhws.applab.restserverspi.database.AbstractMySqlPersistency;
import de.fhws.applab.restserverspi.database.tables.AbstractTable;
import de.fhws.applab.usermanagement.database.config.DefaultMySqlConfig;
import de.fhws.applab.restserverspi.database.config.MySqlConfig;
import de.fhws.applab.usermanagement.database.config.MySqlConfigDispatcher;
import de.fhws.applab.usermanagement.database.tables.UserTable;

import java.util.HashSet;
import java.util.Set;

public class MySqlPersistency extends AbstractMySqlPersistency
{
	private static String DATABASE_NAME = "usermanagement";

	public static final MySqlPersistency getInstance( )
	{
		if ( instance == null )
		{
			instance = new MySqlPersistency( MySqlConfigDispatcher.getMySqlConfig() );
		}

		return (MySqlPersistency)instance;
	}

	public static final MySqlPersistency getInstance( boolean delete )
	{
		if ( instance == null )
		{
			instance = new MySqlPersistency( delete );
		}

		return (MySqlPersistency)instance;
	}

	public MySqlPersistency( )
	{
		super( );
	}

	public MySqlPersistency( boolean deleteDatabase )
	{
		this( new DefaultMySqlConfig( ), deleteDatabase );
	}

	public MySqlPersistency( MySqlConfig configuration )
	{
		this( configuration, false );
	}

	public MySqlPersistency( MySqlConfig configuration, final boolean deleteDatabase )
	{
		super( configuration, deleteDatabase );
	}

	@Override public String getDatabaseName( )
	{
		return DATABASE_NAME;
	}

	@Override protected Set<AbstractTable> getAllTables( )
	{
		Set<AbstractTable> returnValue = new HashSet<>(  );
		returnValue.add( new UserTable() );
		return returnValue;
	}
}
