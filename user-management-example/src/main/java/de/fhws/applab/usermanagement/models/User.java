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

package de.fhws.applab.usermanagement.models;

import com.owlike.genson.annotation.JsonConverter;
import com.owlike.genson.annotation.JsonIgnore;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import de.fhws.applab.restserverspi.api.converters.LinkConverter;
import de.fhws.applab.restserverspi.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlRootElement;

@ApiModel( "The user object model" )
@XmlRootElement
public class User extends AbstractModel
{
	private String emailAddress;

	private String firstName;

	private String lastName;

	private String password;

	@InjectLink( style = InjectLink.Style.ABSOLUTE, value = "users/${instance.id}", rel = "self" )
	private Link selfUri;

	public User( )
	{
	}

	public User( String firstName, String lastName )
	{
		super( );
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName( )
	{
		return firstName;
	}

	@ApiModelProperty( value = "First name", dataType = "String", required = false )
	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName( )
	{
		return lastName;
	}

	@ApiModelProperty( value = "Last name", dataType = "String", required = false )
	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}

	public String getEmailAddress( )
	{
		return emailAddress;
	}

	@ApiModelProperty( value = "Email address", dataType = "String", required = true, notes = "Email address must be unique" )
	public void setEmailAddress( String emailAddress )
	{
		this.emailAddress = emailAddress;
	}

	@JsonConverter( LinkConverter.class )
	public Link getSelfUri( )
	{
		return selfUri;
	}

	@JsonIgnore
	public String getPassword( )
	{
		return password;
	}

	@ApiModelProperty( value = "Password", dataType = "String", required = true )
	public void setPassword( String password )
	{
		this.password = password;
	}

	@Override
	public String toString( )
	{
		return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
