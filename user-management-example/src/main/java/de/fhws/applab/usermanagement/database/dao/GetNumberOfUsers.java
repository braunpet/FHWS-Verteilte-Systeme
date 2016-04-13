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

import de.fhws.applab.restserverspi.database.DatabaseException;
import de.fhws.applab.restserverspi.database.IPersistency;
import de.fhws.applab.restserverspi.database.dao.AbstractGetTotalSizeOfTable;
import de.fhws.applab.usermanagement.database.tables.UserTable;

/**
 * Created by braunpet on 14.06.15.
 */
public class GetNumberOfUsers extends AbstractGetTotalSizeOfTable
{
	public GetNumberOfUsers( IPersistency persistency )
	{
		super( persistency );
	}

	public Long execute( ) throws DatabaseException
	{
		return this.execute( UserTable.TABLE_NAME );
	}
}
