package de.fhws.applab.usermanagement.localcache;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.localcache.AbstractLocalCache;
import de.fhws.applab.usermanagement.database.dao.UserDAO;
import de.fhws.applab.usermanagement.models.User;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Created by braunpet on 19.06.15.
 */
public class LocalUserCache extends AbstractLocalCache<User> implements UserDAO
{
	private UserDAO database;

	public LocalUserCache( long maxCacheSize, UserDAO database )
	{
		super( maxCacheSize );
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

	@Override public User loadUser( final long userId ) throws DatabaseException
	{
		return this.getFromCache( userId, new Callable<User>() {
			@Override public User call( ) throws Exception
			{
				return LocalUserCache.this.loadUser( userId );
			}
		} );
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
}
