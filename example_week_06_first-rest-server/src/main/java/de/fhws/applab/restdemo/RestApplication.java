package de.fhws.applab.restdemo;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import de.fhws.applab.restdemo.api.AccountsService;

@ApplicationPath("/api")
public class RestApplication extends Application
{
	@Override
	public Set<Class<?>> getClasses()
	{
		final Set<Class<?>> returnValue = new HashSet<Class<?>>();
		returnValue.add(AccountsService.class);
		return returnValue;
	}
} 