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

package de.fhws.applab.restserverspi.database.dao;

import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.restserverspi.models.AbstractModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractUpdateOperation<P extends AbstractModel> extends AbstractDatabaseOperation<P, Void>
{
	public AbstractUpdateOperation( IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected ResultSet executeSQLStatement( Statement sqlStatement, String sql ) throws SQLException
	{
		sqlStatement.executeUpdate( sql );
		return null;
	}

	@Override protected Void processResultSet( ResultSet resultSet ) throws SQLException
	{
		return null;
	}
}
