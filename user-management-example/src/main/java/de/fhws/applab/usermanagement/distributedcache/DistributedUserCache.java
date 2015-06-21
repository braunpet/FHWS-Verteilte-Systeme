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
		new UpdateUserCallable( this.database, this.distributedCache, user.getId(), user ).call();
	}

	@Override public User loadUser( long userId ) throws DatabaseException
	{
		return new LoadUserCallable( this.database, this.distributedCache, userId ).call();
	}

	@Override public Collection<User> loadAllUsers( ) throws DatabaseException
	{
		return this.database.loadAllUsers();
	}

	@Override public void deleteUser( long userId ) throws DatabaseException
	{
		new DeleteUserCallable( this.database, this.distributedCache, userId ).call();
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

	@Override public void shutdown( )
	{
		this.database.shutdown();
		super.shutdown();
	}
}
