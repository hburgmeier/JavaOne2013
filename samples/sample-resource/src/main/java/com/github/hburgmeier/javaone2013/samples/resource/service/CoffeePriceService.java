package com.github.hburgmeier.javaone2013.samples.resource.service;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.github.hburgmeier.javaone2013.samples.coffee.CoffeeReadException;
import com.github.hburgmeier.javaone2013.samples.coffee.CoffeeReader;
import com.github.hburgmeier.jerseyoauth2.rs.api.annotations.AllowedScopes;
import com.github.hburgmeier.jerseyoauth2.rs.api.annotations.OAuth20;

@Path("/coffee")
@OAuth20
@AllowedScopes(scopes = {"espresso"})
public class CoffeePriceService {

	private final CoffeeReader coffeeReader = new CoffeeReader();
	private final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.US);
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public CoffeePrice get()
	{
		try {
			String coffePrice = coffeeReader.getCurrentPrice();
			String timestamp = dateFormat.format(new Date());
			return new CoffeePrice(coffePrice, timestamp);
		} catch (CoffeeReadException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
