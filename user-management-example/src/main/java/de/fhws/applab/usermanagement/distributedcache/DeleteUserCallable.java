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
class DeleteUserCallable extends AbstractDistributedCacheCallable<User,Void>
{
	private static final Logger LOGGER = Logger.getLogger( DeleteUserCallable.class );

	private UserDAO userDAO;

	DeleteUserCallable( UserDAO userDAO, IMap<Long,User> distMap, Long id )
	{
		super( distMap, id );
		this.userDAO = userDAO;
	}

	@Override protected Void _call( ) throws DatabaseException
	{
		this.removeFromCache( this.id );
		this.userDAO.deleteUser( this.id );
		return null;
	}
}
