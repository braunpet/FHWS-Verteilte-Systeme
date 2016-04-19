package de.fhws.fiw.applab.pvs.week03.exercise02.mergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class MergeForkJoinWorker extends RecursiveTask<Void>
{
	private static final int THRESHOLD_FOR_SEQUENTIAL_PROCESSING = 20;

	private int[] array;
	private int startIndex;
	private int endIndex;

	public MergeForkJoinWorker( final int[] array )
	{
		this( array, 0, array.length );
	}

	public MergeForkJoinWorker( final int[] array, final int start, final int size )
	{
		this.array = array;
		this.startIndex = start;
		this.endIndex = start + size - 1;
	}

	@Override protected Void compute( )
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

	private Void forkTwoNewProblems( )
	{
		final int leftHalfSize = div2Floor( this.endIndex - this.startIndex + 1 );
		final int rightHalfSize = div2Ceil( this.endIndex - this.startIndex + 1 );
		final int middleIndex = this.startIndex + leftHalfSize;

		final MergeForkJoinWorker forkLeft = createWorker( this.startIndex, leftHalfSize );
		final MergeForkJoinWorker forkRight = createWorker( middleIndex, rightHalfSize );

		forkLeft.fork( );
		forkRight.fork( );

		forkLeft.join( );
		forkRight.join( );

		/* Die folgende Zeile sortiert die beiden Hälften sequentiell. */
		//SequentialMergeOfTwoSortedArrays
		//	.mergeTwoSortedArrays( this.array, this.startIndex, middleIndex - 1, middleIndex, this.endIndex );

		/* Die folgenden Zeilen sortieren die beiden Hälften parallel. */
		final SortForkJoinWorker mergeWorker =
			new SortForkJoinWorker( this.array, this.startIndex, middleIndex - 1, middleIndex, this.endIndex );

		mergeWorker.fork( );
		mergeWorker.join( );

		return null;
	}

	private MergeForkJoinWorker createWorker( final int startIndex, final int size )
	{
		return new MergeForkJoinWorker( this.array, startIndex, size );
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

	private Void computeDirectly( )
	{
		Arrays.sort( this.array, this.startIndex, this.endIndex + 1 );
		return null;
	}
}
