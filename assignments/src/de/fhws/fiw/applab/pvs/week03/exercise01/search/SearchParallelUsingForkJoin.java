package de.fhws.fiw.applab.pvs.week03.exercise01.search;

import de.fhws.fiw.applab.pvs.week02.utils.Measurement;

import java.util.concurrent.ForkJoinPool;

public class SearchParallelUsingForkJoin extends Measurement
{
	private int[] array;

	private ForkJoinPool forkJoinPool;

	private int valueToSearchFor;

	public SearchParallelUsingForkJoin( final int[] array, final int valueToSearchFor )
	{
		this.array = array;
		this.valueToSearchFor = valueToSearchFor;
		this.forkJoinPool = new ForkJoinPool( );
	}

	@Override protected Integer _call( )
	{
		final SearchForkJoinWorker worker = new SearchForkJoinWorker( this.array, this.valueToSearchFor );
		return boolToInt( this.forkJoinPool.invoke( worker ) );
	}

	private int boolToInt( final boolean value )
	{
		return value ? 1 : 0;
	}
}
