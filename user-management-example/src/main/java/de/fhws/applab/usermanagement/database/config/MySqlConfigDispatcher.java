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

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MySqlConfigDispatcher
{
	private static final String JENKINS_SERVER = "193.175.31.139";

	private static final String CLUSTER_SERVER = "10.10.34.";

	private static final String MASTER_DB_SERVER = "10.10.34.2";

	public static MySqlConfig getMySqlConfig( )
	{
		final String host = getHost( );

		if ( host.startsWith( CLUSTER_SERVER ) )
		{
			return new ClusterMySqlConfig( MASTER_DB_SERVER );
		}

		return new DefaultMySqlConfig( );
	}

	private static String getHost( )
	{
		try
		{
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces( );
			while ( interfaces.hasMoreElements( ) )
			{
				NetworkInterface current = interfaces.nextElement( );
				if ( !current.isUp( ) || current.isLoopback( ) || current.isVirtual( ) )
					continue;
				Enumeration<InetAddress> addresses = current.getInetAddresses( );
				while ( addresses.hasMoreElements( ) )
				{
					InetAddress current_addr = addresses.nextElement( );
					if ( current_addr.isLoopbackAddress( ) )
						continue;

					if ( current_addr instanceof Inet4Address )
						return current_addr.getHostAddress( );
				}
			}
		}
		catch ( SocketException e )
		{
			e.printStackTrace( );
		}

		return null;
	}
}
