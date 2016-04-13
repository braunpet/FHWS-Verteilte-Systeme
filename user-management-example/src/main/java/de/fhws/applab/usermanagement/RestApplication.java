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

package de.fhws.applab.usermanagement;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResource;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import de.fhws.applab.usermanagement.api.UsersService;
import de.fhws.applab.usermanagement.api.filters.HttpBasicUserAuthorizationFilter;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath( "/api" )
public class RestApplication extends ResourceConfig
{
	public RestApplication( )
	{
		super( );
		registerClasses( getResourceClasses( ) );
		packages( "org.glassfish.jersey.examples.linking" );
		register( DeclarativeLinkingFeature.class );
		swaggerConfiguration( );
	}

	private Set<Class<?>> getResourceClasses( )
	{
		Set<Class<?>> resources = new HashSet<Class<?>>( );

		resources.add( ApiListingResource.class );
		resources.add( ApiDeclarationProvider.class );
		resources.add( ApiListingResourceJSON.class );
		resources.add( ResourceListingProvider.class );
		resources.add( UsersService.class );
		resources.add( HttpBasicUserAuthorizationFilter.class );

		return resources;
	}

	private void swaggerConfiguration( )
	{
		SwaggerConfig swaggerConfig = new SwaggerConfig( );
		ConfigFactory.setConfig( swaggerConfig );
		swaggerConfig.setApiVersion( "0.0.1" );
		swaggerConfig.setBasePath( "http://localhost:8080/um/api" );
		ScannerFactory.setScanner( new DefaultJaxrsScanner( ) );
		ClassReaders.setReader( new DefaultJaxrsApiReader( ) );
	}
} 