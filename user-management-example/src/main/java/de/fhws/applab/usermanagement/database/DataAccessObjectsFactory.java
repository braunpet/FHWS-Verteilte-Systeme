package de.fhws.applab.usermanagement.database;

import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.database.replication.MasterSlaveUserDAOImpl;
import de.fhws.applab.usermanagement.distributedcache.DistributedUserCache;

import static de.fhws.applab.restserverspi.database.replication.MasterSlavePersistency.withMaster;
import static de.fhws.applab.usermanagement.database.MySqlPersistency.createPersistencyFor;
import static de.fhws.applab.usermanagement.database.config.ClusterNodesMySqlConfig.clusterNode;

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
		//this.userDAO = new UserDAOImpl();
		this.userDAO = new MasterSlaveUserDAOImpl(
				withMaster( createPersistencyFor( clusterNode( 0 ) ) )
				.andSlave( createPersistencyFor( clusterNode( 1 )) ).build() );
		this.distributedUserCache = new DistributedUserCache( this.userDAO );

	}

	public UserDAO createUserDAO( )
	{
		return this.distributedUserCache;
	}

}
