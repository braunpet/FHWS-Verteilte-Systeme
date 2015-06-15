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

	public static MySqlConfig getMySqlConfig( )
	{
		final String host = getHost( );

		if( host.startsWith( CLUSTER_SERVER ))
		{
			return new ClusterMySqlConfig(  );
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
