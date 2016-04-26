package de.fhws.fiw.applab.pvs.week03.exercise02.mergesort;

/**
 * Created by braunpet on 18.04.16.
 */
public class SequentialMergeOfTwoSortedArrays
{
	public static void mergeTwoSortedArrays( final int[] array, final int leftStart, final int leftEnd,
		final int rightStart, final int rightEnd )
	{
		final int[] returnValue = new int[ size( leftStart, leftEnd ) + size( rightStart, rightEnd ) ];

		int leftIndex = leftStart;
		int rightIndex = rightStart;
		int targetIndex = 0;

		while ( leftIndex <= leftEnd && rightIndex <= rightEnd )
		{
			if ( array[ leftIndex ] < array[ rightIndex ] )
			{
				returnValue[ targetIndex++ ] = array[ leftIndex++ ];
			}
			else
			{
				returnValue[ targetIndex++ ] = array[ rightIndex++ ];
			}
		}

		while ( leftIndex <= leftEnd )
		{
			returnValue[ targetIndex++ ] = array[ leftIndex++ ];
		}

		while ( rightIndex <= rightEnd )
		{
			returnValue[ targetIndex++ ] = array[ rightIndex++ ];
		}

		targetIndex = leftStart;
		for ( final int a : returnValue )
		{
			array[ targetIndex++ ] = a;
		}
	}

	private static int size( final int start, final int end )
	{
		return end - start + 1;
	}
}
