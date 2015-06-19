package de.fhws.applab.restserverspi.localcache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.fhws.applab.restserverspi.models.AbstractModel;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by braunpet on 19.06.15.
 */
public class AbstractLocalCache<T extends AbstractModel>
{
	private Cache<Long, T> cache;

	public AbstractLocalCache( long maxCacheSize )
	{
		this.cache = CacheBuilder.newBuilder( ).maximumSize( maxCacheSize ).build( );
	}

	protected final T getFromCache( Long id, Callable<T> loadModel )
	{
		try
		{
			return this.cache.get( id, loadModel );
		}
		catch( ExecutionException e )
		{
			// Ignore
			return null;
		}
	}

	protected final void putToCache( Long id, T object )
	{
		this.cache.put( id, object );
	}

	protected final void removeFromCache( Long id )
	{
		this.cache.invalidate( id );
	}
}
