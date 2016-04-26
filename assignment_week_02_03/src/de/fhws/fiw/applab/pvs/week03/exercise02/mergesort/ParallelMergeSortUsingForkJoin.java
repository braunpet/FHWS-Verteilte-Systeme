package de.fhws.fiw.applab.pvs.week03.exercise02.mergesort;

import de.fhws.fiw.applab.pvs.week02.utils.Measurement;

import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSortUsingForkJoin extends Measurement
{
	private int[] array;

	private ForkJoinPool forkJoinPool;

	public ParallelMergeSortUsingForkJoin( final int[] array )
	{
		this.array = array;
		this.forkJoinPool = new ForkJoinPool( );
	}

	@Override protected Integer _call( )
	{
		final MergeForkJoinWorker worker = new MergeForkJoinWorker( this.array );
		this.forkJoinPool.invoke( worker );
		return 0;
	}
}
