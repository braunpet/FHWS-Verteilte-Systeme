package de.fhws.applab.restserverspi.api.converters;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

import javax.ws.rs.core.Link;

/**
 * Created by braunpet on 14.06.15.
 */
public class LinkConverter implements Converter<Link>
{
	@Override public void serialize( Link link, ObjectWriter objectWriter, Context context ) throws Exception
	{
		objectWriter.writeString( link.getRel(), link.getUri().toASCIIString() );
	}

	@Override public Link deserialize( ObjectReader objectReader, Context context ) throws Exception
	{
		return null;
	}
}
