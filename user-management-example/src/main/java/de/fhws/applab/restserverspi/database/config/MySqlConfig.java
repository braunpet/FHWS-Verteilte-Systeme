/*
 * Copyright (c) peter.braun@fhws.de
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
