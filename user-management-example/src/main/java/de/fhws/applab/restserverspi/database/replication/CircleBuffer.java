package de.fhws.applab.restserverspi.database.replication;

import java.util.List;

/**
 * Created by braunpet on 16.06.15.
 */
class CircleBuffer<T>
{
	private List<T> data;

	private int size;

	private int pointer = 0;

	public CircleBuffer( List<T> data )
	{
		this.data = data;
		this.size = this.data.size( );
	}

	public T next( )
	{
		T returnValue = null;

		synchronized ( this.data )
		{
			returnValue = this.data.get( pointer );
			pointer = ( pointer + 1 ) % size;
		}

		return returnValue;
	}

	public void remove( T elem )
	{
		synchronized ( this.data )
		{
			this.data.remove( elem );
			this.size--;
		}
	}
}
