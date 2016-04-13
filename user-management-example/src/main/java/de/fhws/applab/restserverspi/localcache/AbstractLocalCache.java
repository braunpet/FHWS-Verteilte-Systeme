/*
 * Copyright (c) peter.braun@fhws.de
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
		catch ( ExecutionException e )
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
