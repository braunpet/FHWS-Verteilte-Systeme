package de.fhws.applab.restserverspi.distributedcache;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by braunpet on 21.06.15.
 */
public class HazelcastWrapper
{
	private static HazelcastInstance INSTANCE = null;

	public static synchronized HazelcastInstance getHazelcastInstance()
	{
		if( INSTANCE == null )
		{
			INSTANCE = Hazelcast.newHazelcastInstance( );
		}

		return INSTANCE;
	}


	public static void shutdown()
	{
		Hazelcast.shutdownAll();
	}

}
