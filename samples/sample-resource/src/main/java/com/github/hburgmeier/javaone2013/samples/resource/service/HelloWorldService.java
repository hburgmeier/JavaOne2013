package com.github.hburgmeier.javaone2013.samples.resource.service;

import java.text.MessageFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.github.hburgmeier.jerseyoauth2.rs.api.IOAuthPrincipal;
import com.github.hburgmeier.jerseyoauth2.rs.api.annotations.AllowedScopes;
import com.github.hburgmeier.jerseyoauth2.rs.api.annotations.OAuth20;

@Path("/helloworld")
@OAuth20
// @AllowedScopes(scopes={"HelloWorldScope"})
public class HelloWorldService {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public HelloWorldObject get(@Context SecurityContext context)
	{
		IOAuthPrincipal principal = (IOAuthPrincipal)context.getUserPrincipal();
		return new HelloWorldObject(MessageFormat.format("JavaOne2013 : {0}",principal.getUser().getName()));
	}
	
	
}
