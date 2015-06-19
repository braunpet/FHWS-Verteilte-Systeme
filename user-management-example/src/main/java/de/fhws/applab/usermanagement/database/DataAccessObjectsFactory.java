package de.fhws.applab.usermanagement.database;

import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.database.dao.UserDAOImpl;
import de.fhws.applab.usermanagement.distributedcache.DistributedUserCache;

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

	private DistributedUserCache distributedUserCache;

	private DataAccessObjectsFactory()
	{
		this.userDAO = new UserDAOImpl();
		this.distributedUserCache = new DistributedUserCache( this.userDAO );
	}

	public UserDAO createUserDAO( )
	{
		return this.distributedUserCache;
	}

}
