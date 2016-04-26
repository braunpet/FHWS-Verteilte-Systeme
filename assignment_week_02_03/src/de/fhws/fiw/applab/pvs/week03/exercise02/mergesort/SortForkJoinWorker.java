package de.fhws.fiw.applab.pvs.week03.exercise02.mergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by braunpet on 17.04.16.
 */
public class SortForkJoinWorker extends RecursiveTask<Void>
{
	private static final int THRESHOLD_FOR_SEQUENTIAL_PROCESSING = 20;

	private int[] array;
	private int leftStartIndex;
	private int leftEndIndex;
	private int rightStartIndex;
	private int rightEndIndex;

	public SortForkJoinWorker( final int[] array, final int leftStartIndex, final int leftEndIndex,
		final int rightStartIndex, final int rightEndIndex )
	{
		this.array = array;
		this.leftStartIndex = leftStartIndex;
		this.leftEndIndex = leftEndIndex;
		this.rightStartIndex = rightStartIndex;
		this.rightEndIndex = rightEndIndex;
	}

	@Override protected Void compute( )
	{
		if ( isProblemSmallEnough( ) )
		{
			computeDirectly( );
		}
		else
		{
			forkTwoNewProblems( );
		}

		return null;
	}

	private void forkTwoNewProblems( )
	{
		final int leftHalfSize = div2Floor( this.leftEndIndex - this.leftStartIndex + 1 );
		final int middleIndexOfLeft = this.leftStartIndex + leftHalfSize;
		final int middleValueOfLeft = this.array[ middleIndexOfLeft ];
		final int middleIndexInRight = rankOfElementInRightArray( middleValueOfLeft );
		final int[] newLefts = getNewLeftArray( middleIndexOfLeft, middleIndexInRight );
		final int[] newRights = getNewRightArray( middleIndexOfLeft, middleIndexInRight );

		final SortForkJoinWorker leftFork = createWorker( newLefts, middleIndexOfLeft - this.leftStartIndex );
		final SortForkJoinWorker rightFork = createWorker( newRights, ( this.leftEndIndex - middleIndexOfLeft - 1 ) );

		leftFork.fork( );
		rightFork.fork( );

		leftFork.join( );
		rightFork.join( );

		copyBackIntoLeftHalf( newLefts );
		copyBackIntoRightHalf( newLefts, newRights );
	}

	private void copyBackIntoRightHalf( final int[] newLefts, final int[] newRights )
	{
		System.arraycopy( newRights, 0, this.array, newLefts.length, newRights.length );
	}

	private void copyBackIntoLeftHalf( final int[] newLefts )
	{
		System.arraycopy( newLefts, 0, this.array, 0, newLefts.length );
	}

	private int[] getNewLeftArray( final int middleIndexOfLeft, final int middleIndexInRight )
	{
		final int[] newLefts =
			new int[ ( middleIndexOfLeft - this.leftStartIndex ) + 1 + ( middleIndexInRight - this.rightStartIndex ) ];
		System.arraycopy( this.array, this.leftStartIndex, newLefts, 0, middleIndexOfLeft - this.leftStartIndex + 1 );
		System.arraycopy( this.array, this.rightStartIndex, newLefts, middleIndexOfLeft - this.leftStartIndex + 1,
			( middleIndexInRight - this.rightStartIndex ) );
		return newLefts;
	}

	private int[] getNewRightArray( final int middleIndexOfLeft, final int middleIndexInRight )
	{
		final int[] newRights =
			new int[ ( this.leftEndIndex - middleIndexOfLeft ) + 1 + ( this.rightEndIndex - middleIndexInRight ) ];
		System.arraycopy( this.array, middleIndexOfLeft + 1, newRights, 0, this.leftEndIndex - middleIndexOfLeft );
		System.arraycopy( this.array, middleIndexInRight, newRights, this.leftEndIndex - middleIndexOfLeft,
			( this.rightEndIndex - middleIndexInRight ) + 1 );
		return newRights;
	}

	private int rankOfElementInRightArray( final int value )
	{
		final int pos = Arrays.binarySearch( this.array, this.rightStartIndex, this.rightEndIndex + 1, value );

		if ( pos > 0 )
		{
			int index = pos;
			while ( this.array[ index ] <= value )
			{
				index++;
			}

			return index++;
		}
		else
		{
			return -pos;
		}
	}

	private SortForkJoinWorker createWorker( final int[] data, final int middleIndex )
	{
		return new SortForkJoinWorker( data, 0, middleIndex, middleIndex + 1, data.length - 1 );
	}

	private int div2Floor( final int n )
	{
		return n / 2;
	}

	private boolean isProblemSmallEnough( )
	{
		return Math.min( leftSize( ), rightSize( ) ) <= THRESHOLD_FOR_SEQUENTIAL_PROCESSING;
	}

	private int leftSize( )
	{
		return this.leftEndIndex - this.leftStartIndex;
	}

	private int rightSize( )
	{
		return this.rightEndIndex - this.rightStartIndex;
	}

	private void computeDirectly( )
	{
		SequentialMergeOfTwoSortedArrays.mergeTwoSortedArrays( this.array, this.leftStartIndex, this.leftEndIndex,
			this.rightStartIndex, this.rightEndIndex );
	}
}
