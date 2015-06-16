package de.fhws.applab.usermanagement.models;

import com.owlike.genson.annotation.JsonConverter;
import com.owlike.genson.annotation.JsonIgnore;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import de.fhws.applab.restserverspi.models.AbstractModel;
import de.fhws.applab.usermanagement.api.converters.LinkConverter;
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
	{}

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

	@ApiModelProperty( value = "Email address", dataType = "String", required = true, notes = "Email address must be unique")
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
