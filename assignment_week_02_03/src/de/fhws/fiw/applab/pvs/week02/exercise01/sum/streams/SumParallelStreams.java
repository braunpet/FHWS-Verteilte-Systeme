package de.fhws.fiw.applab.pvs.week02.exercise01.sum.streams;

import de.fhws.fiw.applab.pvs.week02.utils.Measurement;

import java.util.Arrays;

/**
 * Created by braunpet on 12.04.16.
 */
public class SumParallelStreams extends Measurement
{
	private int[] array;

	public SumParallelStreams( final int[] array )
	{
		this.array = array;
	}

	@Override
	protected Integer _call( )
	{
		return Arrays.stream( this.array ).parallel( ).reduce( 0, ( a, b ) -> a + b );
	}
}
