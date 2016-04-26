package de.fhws.applab.mapreduce;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by braunpet on 29.06.15.
 */
public class StartHazelcast
{
	public static void main( String[] args )
	{
		System.out.println( "Map-Reduce Compute Node" );

		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance( );
	}
}
