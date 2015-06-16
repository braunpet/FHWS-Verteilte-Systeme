package de.fhws.applab.usermanagement.database;

import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.database.dao.UserDAOImpl;

public class DataAccessObjectsFactory
{
	private static DataAccessObjectsFactory instance;

	public static DataAccessObjectsFactory getInstance( )
	{
		if( instance == null )
		{
			instance = new DataAccessObjectsFactory();
		}

		return instance;
	}

	private UserDAO userDAO;

	private DataAccessObjectsFactory()
	{
		this.userDAO = new UserDAOImpl();
	}

	public UserDAO createUserDAO( )
	{
		return this.userDAO;
	}

}
