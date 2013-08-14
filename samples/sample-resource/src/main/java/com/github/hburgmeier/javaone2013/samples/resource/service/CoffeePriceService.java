package com.github.hburgmeier.javaone2013.samples.resource.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.github.hburgmeier.javaone2013.samples.coffee.CoffeeReadException;
import com.github.hburgmeier.javaone2013.samples.coffee.CoffeeReader;
import com.github.hburgmeier.jerseyoauth2.rs.api.annotations.OAuth20;

@Path("/coffee")
@OAuth20
public class CoffeePriceService {

	private final CoffeeReader coffeeReader = new CoffeeReader();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public CoffeePrice get(@Context SecurityContext context)
	{
		try {
			String coffePrice = coffeeReader.getCurrentPrice();
			return new CoffeePrice(coffePrice);
		} catch (CoffeeReadException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}