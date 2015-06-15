package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.usermanagement.models.User;

import java.util.Collection;

public class UserDAOImpl implements UserDAO
{
	public UserDAOImpl( )
	{}

	@Override
	public void saveUser( User user ) throws DatabaseException
	{
		new SaveUserOperation( ).execute( user );
	}

	@Override
	public void updateUser( User user ) throws DatabaseException
	{
		new UpdateUserOperation().execute( user );
	}

	@Override
	public void updatePassword( User user ) throws DatabaseException
	{
		new UpdatePasswordOperation().execute( user );
	}

	@Override
	public User loadUser( long userId ) throws DatabaseException
	{
		return new LoadSingleUserByIdOperation().execute( userId );
	}

	@Override
	public Collection<User> loadAllUsers( ) throws DatabaseException
	{
		return new LoadAllUsersOperation().execute();
	}

	@Override
	public void deleteUser( long userId )throws DatabaseException
	{
		new DeleteUserOperation().execute( userId );
	}

	@Override
	public User loadByEmail( String email, String password ) throws DatabaseException
	{
		return new LoadSingleUserByEmailAndPassword().execute( new QueryByEmailAndPassword( email,password ) );
	}


	@Override
	public long getNumberOfUsers( ) throws DatabaseException
	{
		return new GetNumberOfUsers().execute();
	}
}
