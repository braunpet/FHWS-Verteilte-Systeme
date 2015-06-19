package de.fhws.applab.usermanagement.distributedcache;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.distributedcache.AbstractDistributedCache;
import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.models.User;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * Created by braunpet on 18.06.15.
 */
public class DistributedUserCache extends AbstractDistributedCache<User> implements UserDAO
{
	private static final Logger LOGGER = Logger.getLogger( DistributedUserCache.class );

	private UserDAO database;

	public DistributedUserCache( UserDAO database )
	{
		super();
		this.database = database;
	}

	@Override public void saveUser( User user ) throws DatabaseException
	{
		this.database.saveUser( user );
	}

	@Override public void updateUser( User user ) throws DatabaseException
	{
		this.removeFromCache( user.getId() );
		this.database.updateUser( user );
	}

	@Override public User loadUser( long userId ) throws DatabaseException
	{
		User user = this.getFromCache( userId );

		if( user == null )
		{
			LOGGER.debug( "User with id " + userId + " not cached, loading from database" );
			user = this.database.loadUser( userId );
			this.putToCache( userId, user );
		}
		else
		{
			LOGGER.debug( "User with id " + userId + " is cached" );
		}

		return user;
	}

	@Override public Collection<User> loadAllUsers( ) throws DatabaseException
	{
		return this.database.loadAllUsers();
	}

	@Override public void deleteUser( long userId ) throws DatabaseException
	{
		this.removeFromCache( userId );
		this.database.deleteUser( userId );
	}

	@Override public User loadByEmail( String email, String password ) throws DatabaseException
	{
		return this.database.loadByEmail( email, password );
	}

	@Override public long getNumberOfUsers( ) throws DatabaseException
	{
		return this.database.getNumberOfUsers();
	}

	@Override public void updatePassword( User user ) throws DatabaseException
	{

	}

	@Override protected String getDistributedMapName( )
	{
		return "UserCache";
	}
}
