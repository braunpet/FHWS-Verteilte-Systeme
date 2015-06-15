package de.fhws.applab.restserverspi.api.responses;

import java.util.List;

public class Pager
{
	public static <C> List<C> page( List<C> result, int fromIndex, int size )
	{
		final int toIndex = Math.min( result.size( ), fromIndex + size );
		return result.subList( fromIndex, toIndex );
	}
}
