package de.fhws.applab.usermanagement.weblistener;

import de.fhws.applab.restserverspi.distributedcache.HazelcastWrapper;
import de.fhws.applab.usermanagement.database.DataAccessObjectsFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by braunpet on 19.06.15.
 */
@WebListener
public class StartAndStopUserManagement implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		System.out.println("Starting Hazelcast ......" );
		HazelcastWrapper.getHazelcastInstance();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		DataAccessObjectsFactory.getInstance().shutdown();
		HazelcastWrapper.shutdown();
	}
}
