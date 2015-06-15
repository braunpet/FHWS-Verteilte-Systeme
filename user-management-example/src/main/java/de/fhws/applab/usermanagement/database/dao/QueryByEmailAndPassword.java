package de.fhws.applab.usermanagement.database.dao;

import de.fhws.applab.restserverspi.database.dao.AbstractQuery;

/**
 * Created by braunpet on 14.06.15.
 */
public class QueryByEmailAndPassword extends AbstractQuery
{
	private String emailAddress;

	private String password;

	public QueryByEmailAndPassword( String emailAddress, String password )
	{
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public String getEmailAddress( )
	{
		return emailAddress;
	}

	public String getPassword( )
	{
		return password;
	}
}
