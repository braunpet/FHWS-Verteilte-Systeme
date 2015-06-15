package de.fhws.applab.restserverspi.models;

import com.owlike.genson.annotation.JsonIgnore;

/**
 * Created by braunpet on 14.06.15.
 */
public abstract class AbstractModel
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