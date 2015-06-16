package de.fhws.applab.restserverspi.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractLoadByIdOperation<R> extends AbstractLoadSingleOperation<Long,R>
{
	public AbstractLoadByIdOperation( IPersistency persistency )
	{
		super( persistency );
	}
}
