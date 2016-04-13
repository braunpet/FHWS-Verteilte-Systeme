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
		objectWriter.writeString( link.getRel( ), link.getUri( ).toASCIIString( ) );
	}

	@Override public Link deserialize( ObjectReader objectReader, Context context ) throws Exception
	{
		return null;
	}
}
