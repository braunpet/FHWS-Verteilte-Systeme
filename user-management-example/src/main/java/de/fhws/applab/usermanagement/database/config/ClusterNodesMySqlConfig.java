package de.fhws.applab.usermanagement.database.config;

import de.fhws.applab.restserverspi.database.config.MySqlConfig;

/**
 * Created by braunpet on 16.06.15.
 */
public class ClusterNodesMySqlConfig
{
	static
	{
		clusterNodes = new MySqlConfig[] {
			new ClusterMySqlConfig( "10.10.34.2" ),
			new ClusterMySqlConfig( "10.10.34.3" ),
			new ClusterMySqlConfig( "10.10.34.4" ),
			new ClusterMySqlConfig( "10.10.34.5" ),
			new ClusterMySqlConfig( "10.10.34.7" ),
			new ClusterMySqlConfig( "10.10.34.8" ),
			new ClusterMySqlConfig( "10.10.34.9" ),
			new ClusterMySqlConfig( "10.10.34.10" ),
			new ClusterMySqlConfig( "10.10.34.11" ),
			new ClusterMySqlConfig( "10.10.34.12" ),
			new ClusterMySqlConfig( "10.10.34.13" ),
			new ClusterMySqlConfig( "10.10.34.14" ),
			new ClusterMySqlConfig( "10.10.34.15" ),
			new ClusterMySqlConfig( "10.10.34.16" ),
			new ClusterMySqlConfig( "10.10.34.17" )
		};
	}

	private static final MySqlConfig[] clusterNodes;

	public static MySqlConfig clusterNode( int i )
	{
		return clusterNodes[i];
	}
}
