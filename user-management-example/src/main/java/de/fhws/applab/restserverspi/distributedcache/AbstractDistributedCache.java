package de.fhws.applab.restserverspi.distributedcache;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import de.fhws.applab.restserverspi.models.AbstractModel;

/**
 * Created by braunpet on 18.06.15.
 */
public abstract class AbstractDistributedCache<T extends AbstractModel>
{
	private HazelcastInstance hazelcastInstance;

	protected IMap<Long,T> distributedCache;

	public AbstractDistributedCache( )
	{
		this.hazelcastInstance = HazelcastWrapper.getHazelcastInstance();
		this.distributedCache = this.hazelcastInstance.getMap( getDistributedMapName( ) );
	}

	protected void shutdown()
	{

	}

	protected abstract String getDistributedMapName();


}
