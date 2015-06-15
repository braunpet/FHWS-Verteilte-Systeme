package de.fhws.applab.restserverspi.database.config;

public class MySqlConfig
{
	private String dbHost = "localhost";

	private String dbUser = "root"; // braun

	private String dbPassword = "";

	public MySqlConfig( String ipAddress, String dbUser, String dbPassword )
	{
		this.dbHost = ipAddress;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}

	public String getDbHost( )
	{
		return dbHost;
	}

	public String getDbUser( )
	{
		return dbUser;
	}

	public String getDbPassword( )
	{
		return dbPassword;
	}

}
