package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.usermanagement.database.tables.UserTable;
import de.fhws.applab.restserverspi.database.dao.AbstractGetTotalSizeOfTable;

/**
 * Created by braunpet on 14.06.15.
 */
public class GetNumberOfUsers extends AbstractGetTotalSizeOfTable
{
	public GetNumberOfUsers( IPersistency persistency )
	{
		super( persistency );
	}

	public Long execute() throws DatabaseException
	{
		return this.execute( UserTable.TABLE_NAME );
	}
}
