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

package de.fhws.applab.restserverspi.database.replication;

import java.util.List;

/**
 * Created by braunpet on 16.06.15.
 */
class CircleBuffer<T>
{
	private List<T> data;

	private int size;

	private int pointer = 0;

	public CircleBuffer( List<T> data )
	{
		this.data = data;
		this.size = this.data.size( );
	}

	public T next( )
	{
		T returnValue = null;

		synchronized ( this.data )
		{
			returnValue = this.data.get( pointer );
			pointer = ( pointer + 1 ) % size;
		}

		return returnValue;
	}

	public void remove( T elem )
	{
		synchronized ( this.data )
		{
			this.data.remove( elem );
			this.size--;
		}
	}
}
