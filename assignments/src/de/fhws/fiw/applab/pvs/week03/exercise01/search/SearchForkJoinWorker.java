package de.fhws.fiw.applab.pvs.week03.exercise01.search;

import java.util.concurrent.RecursiveTask;

public class SearchForkJoinWorker extends RecursiveTask<Boolean>
{
	private static final int THRESHOLD_FOR_SEQUENTIAL_PROCESSING = 20;

	private int[] array;
	private int startIndex;
	private int endIndex;
	private int valueToSearchFor;

	public SearchForkJoinWorker( final int[] array, final int valueToSearchFor )
	{
		this( array, 0, array.length, valueToSearchFor );
	}

	public SearchForkJoinWorker( final int[] array, final int start, final int size, final int valueToSearchFor )
	{
		this.array = array;
		this.startIndex = start;
		this.endIndex = start + size;
		this.valueToSearchFor = valueToSearchFor;
	}

	@Override protected Boolean compute( )
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

	private boolean forkTwoNewProblems( )
	{
		final int leftHalfSize = div2Floor( this.endIndex - this.startIndex );
		final int rightHalfSize = div2Ceil( this.endIndex - this.startIndex );
		final int middleIndex = this.startIndex + leftHalfSize;

		final SearchForkJoinWorker forkLeft = createWorker( this.startIndex, leftHalfSize );
		final SearchForkJoinWorker forkRight = createWorker( middleIndex, rightHalfSize );

		forkLeft.fork( );
		forkRight.fork( );

		return forkLeft.join( ) || forkRight.join( );
	}

	private SearchForkJoinWorker createWorker( final int startIndex, final int size )
	{
		return new SearchForkJoinWorker( this.array, startIndex, size, this.valueToSearchFor );
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

	private boolean computeDirectly( )
	{
		for ( int i = this.startIndex; i < this.endIndex; i++ )
		{
			if ( this.array[ i ] == this.valueToSearchFor )
			{
				return true;
			}
		}

		return false;
	}
}
