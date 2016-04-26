package de.fhws.fiw.applab.pvs.week02.exercise02.prefix.parallel;

import java.util.concurrent.Callable;

public class AddDeltaWorker implements Callable<Integer>
{
	private int[] array;
	private int size;
	private int start;
	private final int delta;

	public AddDeltaWorker( final int[] array, final int start, final int size, final int delta )
	{
		this.array = array;
		this.start = start;
		this.size = size;
		this.delta = delta;
	}

	@Override
	public Integer call( )
	{
		for ( int i = this.start; i < ( this.start + this.size ); i++ )
		{
			this.array[ i ] += this.delta;
		}

		return this.array[ this.start + this.size - 1 ];
	}
}
