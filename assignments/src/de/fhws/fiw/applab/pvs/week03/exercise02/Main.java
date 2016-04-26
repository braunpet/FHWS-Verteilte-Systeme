package de.fhws.fiw.applab.pvs.week03.exercise02;

import de.fhws.fiw.applab.pvs.week02.utils.InitializeArray;
import de.fhws.fiw.applab.pvs.week03.exercise02.mergesort.ParallelMergeSortUsingForkJoin;
import de.fhws.fiw.applab.pvs.week03.exercise02.mergesort.SequentialMergeOfTwoSortedArrays;

import java.util.Arrays;

public class Main
{
	private static final int ARRAY_SIZE = 70;

	private static final int[] ARRAY = InitializeArray.initializeArrayRandomly( ARRAY_SIZE, 100 );

	public static void main( final String[] args )
	{
		mergeSortUsingForkJoin( );
		//testSequentialMerge( );
	}

	private static void mergeSortUsingForkJoin( )
	{
		final ParallelMergeSortUsingForkJoin s = new ParallelMergeSortUsingForkJoin( ARRAY );

		System.out.println( "Parallel Fork/Join Result: " + s.call( ) + " in " + s.getDuration( ) + " ns" );
		System.out.println( Arrays.toString( ARRAY ) );
	}

	private static void testSequentialMerge( )
	{
		final int[] a = new int[] { 1, 3, 5, 7, 9, 2, 6, 8, 10, 12, 14 };

		SequentialMergeOfTwoSortedArrays.mergeTwoSortedArrays( a, 0, 4, 5, 10 );

		System.out.println( Arrays.toString( a ) );

	}

}
