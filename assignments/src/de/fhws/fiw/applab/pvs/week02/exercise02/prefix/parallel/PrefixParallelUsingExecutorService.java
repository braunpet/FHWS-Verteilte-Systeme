package de.fhws.fiw.applab.pvs.week02.exercise02.prefix.parallel;

import de.fhws.fiw.applab.pvs.week02.exercise02.prefix.sequential.PrefixSequential;
import de.fhws.fiw.applab.pvs.week02.utils.Measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by braunpet on 12.04.16.
 */
public class PrefixParallelUsingExecutorService extends Measurement
{
	private static final int NUMBER_OF_PARALLEL_THREADS = Runtime.getRuntime( ).availableProcessors( );
	private static final int NUMBER_OF_CHUNKS = Runtime.getRuntime( ).availableProcessors( );

	private int[] array;

	private ExecutorService executorService;

	public PrefixParallelUsingExecutorService( final int[] array )
	{
		this.array = array;
		this.executorService = Executors.newFixedThreadPool( NUMBER_OF_PARALLEL_THREADS );
	}

	@Override protected Integer _call( )
	{
		final int[] prefixSumOfChunks = phaseOneForPrefixInChunks( );
		final int[] exclusivePrefixSumOfPrefixSums = phaseTwoForPrefixSumOfPrefixSums( prefixSumOfChunks );

		phaseThreeForAddDeltaToPrefixSumInChunks( exclusivePrefixSumOfPrefixSums );

		return lastElementOfArray( );
	}

	private int lastElementOfArray( )
	{
		return this.array[ this.array.length - 1 ];
	}

	private int[] phaseOneForPrefixInChunks( )
	{
		final PrefixWorker[] workerForPrefix = createThreadsForPrefixInChunks( );
		return waitForAllThreadsToTerminate( submitThreads( workerForPrefix ) );
	}

	private int[] phaseTwoForPrefixSumOfPrefixSums( final int[] prefixSums )
	{
		final int[] shiftedRight = new int[ prefixSums.length ];

		System.arraycopy( prefixSums, 0, shiftedRight, 1, prefixSums.length - 1 );
		new PrefixSequential( shiftedRight ).call( );

		return shiftedRight;
	}

	private void phaseThreeForAddDeltaToPrefixSumInChunks( final int[] deltas )
	{
		final AddDeltaWorker[] workerForAddingDelta = createThreadsForAddingDelta( deltas );
		waitForAllThreadsToTerminate( submitThreads( workerForAddingDelta ) );
	}

	private int[] waitForAllThreadsToTerminate( final List<Future<Integer>> futures )
	{
		final int[] partialResults = new int[ futures.size( ) ];

		for ( int i = 0; i < partialResults.length; i++ )
		{
			try
			{
				partialResults[ i ] = futures.get( i ).get( );
			}
			catch ( InterruptedException | ExecutionException e )
			{
				e.printStackTrace( );
			}
		}

		return partialResults;
	}

	private List<Future<Integer>> submitThreads( final Callable<Integer>[] threads )
	{
		final List<Future<Integer>> futures = new ArrayList<>( );
		for ( int i = 0; i < NUMBER_OF_CHUNKS; i++ )
		{
			futures.add( this.executorService.submit( threads[ i ] ) );
		}
		return futures;
	}

	private PrefixWorker[] createThreadsForPrefixInChunks( )
	{
		final PrefixWorker[] threads = new PrefixWorker[ NUMBER_OF_CHUNKS ];
		final float chunkSize = ( float ) this.array.length / NUMBER_OF_CHUNKS;

		for ( int i = 0; i < NUMBER_OF_CHUNKS; i++ )
		{
			final int from = Math.round( i * chunkSize );
			final int size = Math.round( ( i + 1 ) * chunkSize ) - from;
			threads[ i ] = new PrefixWorker( this.array, from, size );
		}
		return threads;
	}

	private AddDeltaWorker[] createThreadsForAddingDelta( final int[] delta )
	{
		final AddDeltaWorker[] threads = new AddDeltaWorker[ NUMBER_OF_CHUNKS ];
		final float chunkSize = ( float ) this.array.length / NUMBER_OF_CHUNKS;

		for ( int i = 0; i < NUMBER_OF_CHUNKS; i++ )
		{
			final int from = Math.round( i * chunkSize );
			final int size = Math.round( ( i + 1 ) * chunkSize ) - from;
			threads[ i ] = new AddDeltaWorker( this.array, from, size, delta[ i ] );
		}

		return threads;
	}
}
