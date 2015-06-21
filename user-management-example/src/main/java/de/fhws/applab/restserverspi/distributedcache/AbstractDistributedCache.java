package de.fhws.applab.restserverspi.distributedcache;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import de.fhws.applab.restserverspi.models.AbstractModel;
import static de.fhws.applab.usermanagement.weblistener.StartAndStopUserManagement.*;

/**
 * Created by braunpet on 18.06.15.
 */
public abstract class AbstractDistributedCache<T extends AbstractModel>
{
	private HazelcastInstance hazelcastInstance;

	protected IMap<Long,T> distributedCache;

	public AbstractDistributedCache( )
	{
		this.hazelcastInstance = Hazelcast.getHazelcastInstanceByName( USER_MANAGEMENT_HZ_INSTANCE );
		this.distributedCache = this.hazelcastInstance.getMap( getDistributedMapName( ) );
	}

	protected abstract String getDistributedMapName();


}
