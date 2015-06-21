package de.fhws.applab.usermanagement.weblistener;

import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import de.fhws.applab.usermanagement.database.MySqlPersistency;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by braunpet on 19.06.15.
 */
@WebListener
public class StartAndStopUserManagement implements ServletContextListener
{
	public static final String USER_MANAGEMENT_HZ_INSTANCE = "UserManagement-HazelcastInstance";

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		try
		{
			Hazelcast.getOrCreateHazelcastInstance( createHazelcastConfig( ) );
		}
		catch( Throwable t )
		{
			System.out.println( t.getMessage() );
			t.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		Hazelcast.getHazelcastInstanceByName( USER_MANAGEMENT_HZ_INSTANCE ).shutdown();
		MySqlPersistency.shutdown();
	}

	private Config createHazelcastConfig()
	{
		Config returnValue = new Config();

		GroupConfig groupConfig = returnValue.getGroupConfig( );
		groupConfig.setName( "braun" );
		groupConfig.setPassword( "braun-passwd" );

		NetworkConfig network = returnValue.getNetworkConfig();
		JoinConfig join = network.getJoin();
		join.getMulticastConfig().setEnabled( true );

		returnValue.setInstanceName( USER_MANAGEMENT_HZ_INSTANCE );

		return returnValue;
	}
}
