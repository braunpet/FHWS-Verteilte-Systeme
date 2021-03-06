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

package de.fhws.applab.restserverspi.database.replication;

import de.fhws.applab.restserverspi.database.IPersistency;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by braunpet on 16.06.15.
 */
public class MasterSlavePersistency
{
	private IPersistency master;

	private Collection<IPersistency> slaves;

	private CircleBuffer<IPersistency> roundRobinBuffer;

	public static MasterSlavePersistencyBuilder withMaster( IPersistency master )
	{
		return new MasterSlavePersistencyBuilder( master );
	}

	private MasterSlavePersistency( IPersistency master, Collection<IPersistency> slaves )
	{
		this.master = master;
		this.slaves = slaves;
		this.roundRobinBuffer = new CircleBuffer<>( new LinkedList<>( this.slaves ) );
	}

	public IPersistency getMaster( )
	{
		return this.master;
	}

	public IPersistency getSlave( )
	{
		if ( this.slaves.isEmpty( ) )
		{
			return this.master;
		}
		else
		{
			return this.roundRobinBuffer.next( );
		}
	}

	public void shutdown( )
	{
		this.master.shutdown( );

		for ( IPersistency slave : this.slaves )
		{
			slave.shutdown( );
		}
	}

	public static class MasterSlavePersistencyBuilder
	{
		private IPersistency master;

		private Collection<IPersistency> slaves;

		public MasterSlavePersistencyBuilder( IPersistency master )
		{
			this.master = master;
			this.slaves = new LinkedList<IPersistency>( );
		}

		public MasterSlavePersistencyBuilder andSlave( IPersistency slave )
		{
			this.slaves.add( slave );
			return this;
		}

		public MasterSlavePersistencyBuilder andSlaves( IPersistency... slaves )
		{
			this.slaves.addAll( Arrays.asList( slaves ) );
			return this;
		}

		public MasterSlavePersistency build( )
		{
			return new MasterSlavePersistency( this.master, this.slaves );
		}
	}
}
