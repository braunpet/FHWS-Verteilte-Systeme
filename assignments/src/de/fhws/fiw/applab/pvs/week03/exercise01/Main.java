package de.fhws.fiw.applab.pvs.week03.exercise01;

import de.fhws.fiw.applab.pvs.week02.utils.InitializeArray;
import de.fhws.fiw.applab.pvs.week03.exercise01.search.SearchParallelUsingForkJoin;

public class Main
{
	private static final int ARRAY_SIZE = 20000;

	private static final int[] ARRAY = InitializeArray.initializeArray( ARRAY_SIZE );

	public static void main( final String[] args )
	{
		searchParallelUsingForkJoin( );
	}

	private static void searchParallelUsingForkJoin( )
	{
		final SearchParallelUsingForkJoin s = new SearchParallelUsingForkJoin( ARRAY, ARRAY[ 0 ] );

		System.out.println( "Parallel Fork/Join Result: " + s.call( ) + " in " + s.getDuration( ) + " ns" );
	}

	private static int randomInt( )
	{
		return Math.round( ( ( float ) Math.random( ) * Integer.MAX_VALUE ) );
	}
}
