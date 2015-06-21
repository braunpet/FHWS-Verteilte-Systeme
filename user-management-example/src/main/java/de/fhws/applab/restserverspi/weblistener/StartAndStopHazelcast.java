package de.fhws.applab.restserverspi.weblistener;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by braunpet on 19.06.15.
 */
@WebListener
public class StartAndStopHazelcast implements ServletContextListener
{
	public static final String USER_MANAGEMENT_HZ_INSTANCE = "UserManagement-HazelcastInstance";

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		Hazelcast.newHazelcastInstance( createHazelcastConfig() );
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		Hazelcast.shutdownAll( );
	}

	private Config createHazelcastConfig()
	{
		Config returnValue = new Config();

		returnValue.setInstanceName( USER_MANAGEMENT_HZ_INSTANCE );

		return returnValue;
	}
}
