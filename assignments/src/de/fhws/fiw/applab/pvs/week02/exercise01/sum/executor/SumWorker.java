package de.fhws.fiw.applab.pvs.week02.exercise01.sum.executor;

import java.util.concurrent.Callable;

public class SumWorker implements Callable<Integer>
{

	private final int[] array;
	private int number;
	private int start;

	public SumWorker( final int[] array, final int start, final int number )
	{
		this.array = array;
		this.start = start;
		this.number = number;
	}

	@Override
	public Integer call( )
	{
		int sum = 0;
		for ( int i = this.start; i < ( this.start + this.number ); i++ )
		{
			sum = sum + this.array[ i ];
		}
		return sum;
	}
}
