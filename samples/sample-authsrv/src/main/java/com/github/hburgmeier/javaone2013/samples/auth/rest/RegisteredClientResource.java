package com.github.hburgmeier.javaone2013.samples.auth.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.ClientServiceException;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IClientService;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IRegisteredClientApp;
import com.github.hburgmeier.jerseyoauth2.authsrv.impl.simple.SimpleRegisteredClient;

@Path("/registeredClients")
public class RegisteredClientResource {

	protected final IClientService clientService;
	
	@Inject
	public RegisteredClientResource(IClientService clientService)
	{
		this.clientService = clientService;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public IRegisteredClientApp createClient(SimpleRegisteredClient client)
	{
		try {
			return clientService.registerClient(client.getApplicationName(), client.getCallbackUrl(), client.getClientType());
		} catch (ClientServiceException e) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
	}
	
}
