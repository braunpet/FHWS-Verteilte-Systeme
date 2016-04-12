package de.fhws.fiw.applab.pvs.week02.exercise01.sum.executor;

import de.fhws.fiw.applab.pvs.week02.utils.Measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by braunpet on 12.04.16.
 */
public class SumParallelUsingExecutorService extends Measurement
{
	private static final int NUMBER_OF_PARALLEL_THREADS = 4;
	private static final int NUMBER_OF_CHUNKS = 10;

	private int[] array;

	private final ExecutorService executorService;

	public SumParallelUsingExecutorService( final int[] array )
	{
		this.array = array;
		this.executorService = Executors.newFixedThreadPool( NUMBER_OF_PARALLEL_THREADS );
	}

	@Override
	protected Integer _call( )
	{
		final SumWorker[] workers = createThreadsForChunks( );
		return waitForAllThreadsToTerminate( submitThreads( workers ) );
	}

	private SumWorker[] createThreadsForChunks( )
	{
		final SumWorker[] threads = new SumWorker[ NUMBER_OF_CHUNKS ];
		final int chunkSize = this.array.length / NUMBER_OF_CHUNKS;

		for ( int i = 0; i < NUMBER_OF_CHUNKS; i++ )
		{
			threads[ i ] = new SumWorker( this.array, i * chunkSize, chunkSize );
		}
		return threads;
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

	private int waitForAllThreadsToTerminate( final List<Future<Integer>> futures )
	{
		int returnValue = 0;

		for ( final Future<Integer> f : futures )
		{
			try
			{
				returnValue += f.get( );
			}
			catch ( InterruptedException | ExecutionException e )
			{
				e.printStackTrace( );
			}
		}

		return returnValue;
	}

}
