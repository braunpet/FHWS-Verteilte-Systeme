package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.usermanagement.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static de.fhws.applab.restserverspi.database.tables.AbstractTable.COL_ID;
import static de.fhws.applab.usermanagement.database.tables.UserTable.*;

/**
 * Created by braunpet on 14.06.15.
 */
public class DatabaseUserModel
{
	public static User createUser( ResultSet rs ) throws SQLException
	{
		User returnValue = new User( );
		returnValue.setId( rs.getLong( COL_ID ) );
		returnValue.setEmailAddress( rs.getString( COL_EMAIL ) );
		returnValue.setPassword( rs.getString( COL_PASSWORD ) );
		returnValue.setFirstName( rs.getString( COL_FIRSTNAME ) );
		returnValue.setLastName( rs.getString( COL_LASTNAME ) );
		returnValue.setLastModifiedAt( rs.getLong( COL_LASTMODIFIED ) );
		return returnValue;
	}
}
