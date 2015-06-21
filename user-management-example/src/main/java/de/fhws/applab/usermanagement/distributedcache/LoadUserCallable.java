package de.fhws.applab.usermanagement.distributedcache;

import com.hazelcast.core.IMap;
import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.distributedcache.AbstractDistributedCacheCallable;
import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.models.User;
import org.apache.log4j.Logger;

/**
 * Created by braunpet on 20.06.15.
 */
class LoadUserCallable extends AbstractDistributedCacheCallable<User,User>
{
	private static final Logger LOGGER = Logger.getLogger( LoadUserCallable.class );

	private UserDAO userDAO;

	LoadUserCallable( UserDAO userDAO, IMap<Long,User> distMap, Long id )
	{
		super( distMap, id );
		this.userDAO = userDAO;
	}

	@Override protected User _call( ) throws DatabaseException
	{
		User user = this.getFromCache( this.id );

		if( user == null )
		{
			LOGGER.debug( "User with id " + this.id + " not cached, loading from database" );

			user = this.userDAO.loadUser( this.id );
			this.putToCache( this.id, user );
		}
		else
		{
			LOGGER.debug( "User with id " + this.id + " is cached" );
		}

		return user;
	}
}
