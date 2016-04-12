package de.fhws.fiw.applab.pvs.week02.exercise01.sum.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Created by braunpet on 12.04.16.
 */
public class SumForkJoinWorker extends RecursiveTask<Integer>
{
	private static final int THRESHOLD_FOR_SEQUENTIAL_PROCESSING = 20;

	private int[] array;
	private int startIndex;
	private int endIndex;

	public SumForkJoinWorker( final int[] array, final int start, final int size )
	{
		this.array = array;
		this.startIndex = start;
		this.endIndex = start + size;
	}

	@Override protected Integer compute( )
	{
		if ( isProblemSmallEnough( ) )
		{
			return computeDirectly( );
		}
		else
		{
			return forkTwoNewProblems( );
		}
	}

	private int forkTwoNewProblems( )
	{
		final int leftHalfSize = div2Floor( this.endIndex - this.startIndex );
		final int rightHalfSize = div2Ceil( this.endIndex - this.startIndex );
		final int middleIndex = this.startIndex + leftHalfSize;

		final SumForkJoinWorker forkLeft = new SumForkJoinWorker( this.array, this.startIndex, leftHalfSize );
		final SumForkJoinWorker forkRight = new SumForkJoinWorker( this.array, middleIndex, rightHalfSize );

		forkLeft.fork( );
		forkRight.fork( );

		return forkLeft.join( ) + forkRight.join( );
	}

	private int div2Floor( final int n )
	{
		return n / 2;
	}

	private int div2Ceil( final int n )
	{
		return n / 2 + ( n % 2 );
	}

	private boolean isProblemSmallEnough( )
	{
		return this.endIndex - this.startIndex <= THRESHOLD_FOR_SEQUENTIAL_PROCESSING;
	}

	private Integer computeDirectly( )
	{
		int sum = 0;

		for ( int i = this.startIndex; i < this.endIndex; i++ )
		{
			sum += this.array[ i ];
		}

		return sum;
	}
}
