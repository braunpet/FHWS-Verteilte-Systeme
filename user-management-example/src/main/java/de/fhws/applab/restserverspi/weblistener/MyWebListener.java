package de.fhws.applab.restserverspi.weblistener;

import com.hazelcast.core.Hazelcast;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by braunpet on 19.06.15.
 */
@WebListener
public class MyWebListener implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Do your job here during webapp startup.
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		Hazelcast.shutdownAll( );
	}
}
