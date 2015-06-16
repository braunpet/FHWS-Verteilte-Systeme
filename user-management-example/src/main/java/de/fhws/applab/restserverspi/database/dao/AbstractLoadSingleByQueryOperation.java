package de.fhws.applab.restserverspi.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractLoadSingleByQueryOperation<P extends AbstractQuery,R>  extends AbstractLoadSingleOperation<P,R>
{
	public AbstractLoadSingleByQueryOperation( IPersistency persistency )
	{
		super( persistency );
	}
}
