package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.usermanagement.database.MySqlPersistency;
import de.fhws.applab.usermanagement.database.config.MySqlConfigDispatcher;
import de.fhws.applab.usermanagement.models.User;

import java.util.Collection;

public class UserDAOImpl implements UserDAO
{
	private IPersistency persistency;

	public UserDAOImpl( )
	{
		this.persistency = new MySqlPersistency( MySqlConfigDispatcher.getMySqlConfig() );
	}

	@Override
	public void saveUser( User user ) throws DatabaseException
	{
		new SaveUserOperation( this.persistency ).execute( user );
	}

	@Override
	public void updateUser( User user ) throws DatabaseException
	{
		new UpdateUserOperation(this.persistency).execute( user );
	}

	@Override
	public void updatePassword( User user ) throws DatabaseException
	{
		new UpdatePasswordOperation(this.persistency).execute( user );
	}

	@Override
	public User loadUser( long userId ) throws DatabaseException
	{
		return new LoadSingleUserByIdOperation(this.persistency).execute( userId );
	}

	@Override
	public Collection<User> loadAllUsers( ) throws DatabaseException
	{
		return new LoadAllUsersOperation(this.persistency).execute();
	}

	@Override
	public void deleteUser( long userId )throws DatabaseException
	{
		new DeleteUserOperation(this.persistency).execute( userId );
	}

	@Override
	public User loadByEmail( String email, String password ) throws DatabaseException
	{
		return new LoadSingleUserByEmailAndPassword(this.persistency).execute( new QueryByEmailAndPassword( email,password ) );
	}


	@Override
	public long getNumberOfUsers( ) throws DatabaseException
	{
		return new GetNumberOfUsers(this.persistency).execute();
	}
}
