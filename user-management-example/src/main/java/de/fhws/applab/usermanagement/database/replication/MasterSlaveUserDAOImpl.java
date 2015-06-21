package de.fhws.applab.usermanagement.database.replication;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.database.replication.MasterSlavePersistency;
import de.fhws.applab.usermanagement.database.dao.*;
import de.fhws.applab.usermanagement.models.User;

import java.util.Collection;

/**
 * Created by braunpet on 16.06.15.
 */
public class MasterSlaveUserDAOImpl implements UserDAO
{
	private MasterSlavePersistency persistency;

	public MasterSlaveUserDAOImpl( MasterSlavePersistency persistency )
	{
		this.persistency = persistency;
	}

	@Override
	public void saveUser( User user ) throws DatabaseException
	{
		new SaveUserOperation( this.persistency.getMaster() ).execute( user );
	}

	@Override
	public void updateUser( User user ) throws DatabaseException
	{
		new UpdateUserOperation(this.persistency.getMaster() ).execute( user );
	}

	@Override
	public void updatePassword( User user ) throws DatabaseException
	{
		new UpdatePasswordOperation(this.persistency.getMaster() ).execute( user );
	}

	@Override
	public User loadUser( long userId ) throws DatabaseException
	{
		return new LoadSingleUserByIdOperation(this.persistency.getSlave() ).execute( userId );
	}

	@Override
	public Collection<User> loadAllUsers( ) throws DatabaseException
	{
		return new LoadAllUsersOperation(this.persistency.getSlave() ).execute();
	}

	@Override
	public void deleteUser( long userId )throws DatabaseException
	{
		new DeleteUserOperation(this.persistency.getMaster() ).execute( userId );
	}

	@Override
	public User loadByEmail( String email, String password ) throws DatabaseException
	{
		return new LoadSingleUserByEmailAndPassword(this.persistency.getSlave() ).execute( new QueryByEmailAndPassword( email,password ) );
	}


	@Override
	public long getNumberOfUsers( ) throws DatabaseException
	{
		return new GetNumberOfUsers(this.persistency.getSlave() ).execute();
	}

	@Override public void shutdown( )
	{
		this.persistency.shutdown();
	}
}
