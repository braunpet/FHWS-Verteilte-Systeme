package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.usermanagement.models.User;

import java.util.Collection;

public interface UserDAO
{
	void saveUser( User user ) throws DatabaseException;

	void updateUser( User user ) throws DatabaseException;

	User loadUser( long userId ) throws DatabaseException;

	Collection<User> loadAllUsers( ) throws DatabaseException;

	void deleteUser( long userId ) throws DatabaseException;

	User loadByEmail( String email, String password ) throws DatabaseException;
	
	long getNumberOfUsers( ) throws DatabaseException;

	void updatePassword( User user ) throws DatabaseException;

	void shutdown();
}
