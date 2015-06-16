package de.fhws.applab.usermanagement.database.config;

import de.fhws.applab.restserverspi.database.config.MySqlConfig;

/**
 * Created by braunpet on 16.06.15.
 */
public class ClusterMySqlConfig extends MySqlConfig
{
	private final static String USER = "studadmin";

	private final static String PASS = "studpasswd";

	public ClusterMySqlConfig( String ipAddress )
	{
		super( ipAddress, USER, PASS );
	}
}
