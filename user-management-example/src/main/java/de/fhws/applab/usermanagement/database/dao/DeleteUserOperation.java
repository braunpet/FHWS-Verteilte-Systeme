package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.usermanagement.database.tables.UserTable;
import de.fhws.applab.restserverspi.database.dao.AbstractDeleteOperation;

/**
 * Created by braunpet on 14.06.15.
 */
public class DeleteUserOperation extends AbstractDeleteOperation
{
	public DeleteUserOperation( IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected String createSQLStatement( )
	{
		return "DELETE FROM " + UserTable.TABLE_NAME + " WHERE id = " + this.params.toString();
	}
}
