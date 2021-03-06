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

package de.fhws.applab.restserverspi.models;

import com.owlike.genson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractModel implements Serializable
{
	protected long id;

	protected long lastModifiedAt;

	public AbstractModel( )
	{
	}

	public long getId( )
	{
		return id;
	}

	public void setId( long id )
	{
		this.id = id;
	}

	public void setLastModifiedAt( long lastModifiedAt )
	{
		this.lastModifiedAt = lastModifiedAt;
	}

	@JsonIgnore
	public long getLastModifiedAt( )
	{
		return lastModifiedAt;
	}
}
