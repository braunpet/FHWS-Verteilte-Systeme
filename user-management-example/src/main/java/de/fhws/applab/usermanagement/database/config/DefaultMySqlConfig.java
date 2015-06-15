package de.fhws.applab.usermanagement.database.config;

import de.fhws.applab.restserverspi.database.config.MySqlConfig;

public class DefaultMySqlConfig extends MySqlConfig
{
	public DefaultMySqlConfig( )
	{
		super( "localhost", "braunp", "willi" );
	}

}
