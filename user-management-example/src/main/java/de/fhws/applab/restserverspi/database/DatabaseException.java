package de.fhws.applab.restserverspi.database;

/**
 * Created by braunpet on 14.06.15.
 */
public class DatabaseException extends Exception
{
	public DatabaseException( )
	{
	}

	public DatabaseException( String message )
	{
		super( message );
	}

	public DatabaseException( String message, Throwable cause )
	{
		super( message, cause );
	}
}
