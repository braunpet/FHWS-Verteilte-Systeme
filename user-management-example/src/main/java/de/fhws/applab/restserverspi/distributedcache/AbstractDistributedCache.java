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

	protected IMap<Long, T> distributedCache;

	public AbstractDistributedCache( )
	{
		this.hazelcastInstance = HazelcastWrapper.getHazelcastInstance( );
		this.distributedCache = this.hazelcastInstance.getMap( getDistributedMapName( ) );
	}

	protected void shutdown( )
	{
	}

	protected abstract String getDistributedMapName( );

}
