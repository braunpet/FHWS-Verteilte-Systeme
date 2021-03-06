package de.fhws.fiw.applab.pvs.week02.utils;

/**
 * Created by braunpet on 12.04.16.
 */
public class InitializeArray
{
	public static int[] initializeArray( final int n )
	{
		final int[] array = new int[ n ];
		for ( int i = 0; i < n; i++ )
		{
			array[ i ] = i;
		}
		return array;
	}

	public static int[] initializeArrayRandomly( final int n, final int limit )
	{
		final int[] array = new int[ n ];
		for ( int i = 0; i < n; i++ )
		{
			array[ i ] = randomInt( limit );
		}
		return array;
	}

	private static int randomInt( final int limit )
	{
		return Math.round( ( ( float ) Math.random( ) * limit ) );
	}
}
