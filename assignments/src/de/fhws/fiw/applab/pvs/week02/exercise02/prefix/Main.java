package de.fhws.fiw.applab.pvs.week02.exercise02.prefix;

import de.fhws.fiw.applab.pvs.week02.exercise02.prefix.parallel.PrefixParallelUsingExecutorService;
import de.fhws.fiw.applab.pvs.week02.exercise02.prefix.sequential.PrefixSequential;
import de.fhws.fiw.applab.pvs.week02.utils.InitializeArray;

public class Main
{
	private static final int ARRAY_SIZE = 20;

	public static void main( final String[] args )
	{
		prefixSequential( );
		prefixUsingExecutorService( );
	}

	private static void prefixSequential( )
	{
		final int[] array = InitializeArray.initializeArray( ARRAY_SIZE );
		final PrefixSequential s = new PrefixSequential( array );

		System.out.println( "Sequential Result: " + s.call( ) + " in " + s.getDuration( ) + " ns" );
	}

	private static void prefixUsingExecutorService( )
	{
		final int[] array = InitializeArray.initializeArray( ARRAY_SIZE );
		final PrefixParallelUsingExecutorService p = new PrefixParallelUsingExecutorService( array );

		System.out.println( "Parallel Result: " + p.call( ) + " in " + p.getDuration( ) + " ns" );
	}
}
