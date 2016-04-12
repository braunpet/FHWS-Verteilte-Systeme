package de.fhws.fiw.applab.pvs.week02.exercise01.sum;

import de.fhws.fiw.applab.pvs.week02.exercise01.sum.executor.SumParallelUsingExecutorService;
import de.fhws.fiw.applab.pvs.week02.exercise01.sum.forkjoin.SumParallelUsingForkJoin;
import de.fhws.fiw.applab.pvs.week02.exercise01.sum.sequential.Sequential;
import de.fhws.fiw.applab.pvs.week02.exercise01.sum.streams.SumParallelStreams;
import de.fhws.fiw.applab.pvs.week02.utils.InitializeArray;

public class Main
{
	private static final int ARRAY_SIZE = 3000;

	private static final int[] ARRAY = InitializeArray.initializeArray( ARRAY_SIZE );

	public static void main( final String[] args )
	{
		sumSequential( );
		sumParallelUsingExecutorService( );
		sumParallelUsingForkJoin( );
		sumParallelUsingStream( );
	}

	private static void sumSequential( )
	{
		final Sequential s = new Sequential( ARRAY );

		System.out.println( "Sequential Result: " + s.call( ) + " in " + s.getDuration( ) + " ns" );
	}

	private static void sumParallelUsingExecutorService( )
	{
		final SumParallelUsingExecutorService s = new SumParallelUsingExecutorService( ARRAY );

		System.out.println( "Parallel Executor Result: " + s.call( ) + " in " + s.getDuration( ) + " ns" );

	}

	private static void sumParallelUsingForkJoin( )
	{
		final SumParallelUsingForkJoin s = new SumParallelUsingForkJoin( ARRAY );

		System.out.println( "Parallel Fork/Join Result: " + s.call( ) + " in " + s.getDuration( ) + " ns" );

	}

	private static void sumParallelUsingStream( )
	{
		final SumParallelStreams s = new SumParallelStreams( ARRAY );

		System.out.println( "Parallel Stream Result: " + s.call( ) + " in " + s.getDuration( ) + " ns" );
	}

}
