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
class UpdateUserCallable extends AbstractDistributedCacheCallable<User,Void>
{
	private static final Logger LOGGER = Logger.getLogger( UpdateUserCallable.class );

	private UserDAO userDAO;

	UpdateUserCallable( UserDAO userDAO, IMap<Long,User> distMap, Long id, User newUser )
	{
		super( distMap, id, newUser );
		this.userDAO = userDAO;
	}

	@Override protected Void _call( ) throws DatabaseException
	{
		this.removeFromCache( this.object.getId() );
		this.userDAO.updateUser( this.object );
		return null;
	}
}
