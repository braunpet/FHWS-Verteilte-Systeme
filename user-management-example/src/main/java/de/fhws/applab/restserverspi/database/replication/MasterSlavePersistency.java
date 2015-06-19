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

	public IPersistency getMaster()
	{
		return this.master;
	}

	public IPersistency getSlave()
	{
		if( this.slaves.isEmpty() )
		{
			return this.master;
		}
		else
		{
			return this.roundRobinBuffer.next( );
		}
	}

	public static class MasterSlavePersistencyBuilder
	{
		private IPersistency master;

		private Collection<IPersistency> slaves;

		public MasterSlavePersistencyBuilder( IPersistency master )
		{
			this.master = master;
			this.slaves = new LinkedList<IPersistency>();
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

		public MasterSlavePersistency build()
		{
			return new MasterSlavePersistency( this.master, this.slaves );
		}
	}
}
