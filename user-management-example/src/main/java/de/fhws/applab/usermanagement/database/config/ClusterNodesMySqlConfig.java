/*
 * Copyright (c) peter.braun@fhws.de
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
		return clusterNodes[ i ];
	}
}
