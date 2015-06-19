package de.fhws.applab.restserverspi.distributedcache;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import de.fhws.applab.restserverspi.models.AbstractModel;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by braunpet on 18.06.15.
 */
public abstract class AbstractDistributedCache<T extends AbstractModel>
{
	private HazelcastInstance hazelcastInstance;

	private ConcurrentMap<Long,T> distributedCache;

	public AbstractDistributedCache( )
	{
		this.hazelcastInstance = Hazelcast.newHazelcastInstance( );
		this.distributedCache = this.hazelcastInstance.getMap( getDistributedMapName() );
	}

	protected abstract String getDistributedMapName();

	protected final T getFromCache( Long id )
	{
		return this.distributedCache.get( id );
	}

	protected final void putToCache( Long id, T object )
	{
		this.distributedCache.put( id, object );
	}

	protected final void removeFromCache( Long id )
	{
		this.distributedCache.remove( id );
	}
}
