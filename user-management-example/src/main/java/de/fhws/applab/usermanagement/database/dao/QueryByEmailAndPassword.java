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
