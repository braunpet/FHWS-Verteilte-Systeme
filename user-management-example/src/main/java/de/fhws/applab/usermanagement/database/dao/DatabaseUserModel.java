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
