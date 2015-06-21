package de.fhws.applab.restserverspi.distributedcache;

import com.hazelcast.core.IMap;
import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.models.AbstractModel;

import java.util.concurrent.Callable;

/**
 * Created by braunpet on 20.06.15.
 */
public abstract class AbstractDistributedCacheCallable<P extends AbstractModel,R> implements Callable<R>
{
	private final IMap<Long,P> distributedMap;

	protected final Long id;

	protected P object;

	protected AbstractDistributedCacheCallable( IMap<Long,P> distMap, Long id )
	{
		this.distributedMap = distMap;
		this.id = id;
	}

	protected AbstractDistributedCacheCallable( IMap<Long,P> distMap, Long id, P object )
	{
		this( distMap, id );
		this.object = object;
	}

	@Override final public R call( ) throws DatabaseException
	{
		try
		{
			this.distributedMap.lock( this.id );

			return _call();
		}
		finally
		{
			this.distributedMap.unlock( this.id );
		}
	}

	protected abstract R _call() throws DatabaseException;

	protected final P getFromCache( Long id )
	{
		return this.distributedMap.get( id );
	}

	protected final void putToCache( Long id, P object )
	{
		this.distributedMap.put( id, object );
	}

	protected final void removeFromCache( Long id )
	{
		this.distributedMap.remove( id );
	}

}