/*
 * Copyright (c) peter.braun@fhws.de
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
		if ( instance == null )
		{
			instance = new DataAccessObjectsFactory( );
		}

		return instance;
	}

	private UserDAO userDAO;

	private DistributedUserCache distributedUserCache;

	private DataAccessObjectsFactory( )
	{
		//this.userDAO = new UserDAOImpl();
		this.userDAO = new MasterSlaveUserDAOImpl(
			withMaster( createPersistencyFor( clusterNode( 0 ) ) )
				.andSlave( createPersistencyFor( clusterNode( 1 ) ) ).build( ) );
		this.distributedUserCache = new DistributedUserCache( this.userDAO );

	}

	public void shutdown( )
	{
		this.distributedUserCache.shutdown( );
	}

	public UserDAO createUserDAO( )
	{
		return this.distributedUserCache;
	}

}
