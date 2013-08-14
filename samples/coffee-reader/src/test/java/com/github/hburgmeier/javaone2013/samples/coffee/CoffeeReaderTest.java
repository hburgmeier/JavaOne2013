package com.github.hburgmeier.javaone2013.samples.coffee;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CoffeeReaderTest {

	protected CoffeeReader reader = new CoffeeReader();
	
	@Test
	public void testReader() throws CoffeeReadException
	{
		String currentPrice = reader.getCurrentPrice();
		assertNotNull(currentPrice);
		assertNotEquals("unknown", currentPrice);
		
		try {
			Float.parseFloat(currentPrice);
		} catch (NumberFormatException e) {
			fail();
		}
	}
	
}
