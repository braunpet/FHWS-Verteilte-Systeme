package de.fhws.applab.usermanagement.database.config;

import de.fhws.applab.restserverspi.database.config.MySqlConfig;

/**
 * Created by braunpet on 15.06.15.
 */
public class ClusterMySqlConfig extends MySqlConfig
{
	public ClusterMySqlConfig( )
	{
		super( "10.10.34.2", "studadmin", "studpasswd" );
	}
}
