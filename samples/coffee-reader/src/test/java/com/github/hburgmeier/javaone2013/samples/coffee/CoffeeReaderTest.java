package com.github.hburgmeier.javaone2013.samples.coffee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

public class CoffeeReaderTest {

	protected CoffeeReader reader = new CoffeeReader();
	
	@Ignore // does not work on Mondays
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
	
	@Test
	public void testParse() throws CoffeeReadException, URISyntaxException, IOException
	{
		File prices = new File(this.getClass().getResource("/prices.html").toURI());
		String priceTable = FileUtils.readFileToString(prices);
		String currentPrice = reader.parseHtmlTable(priceTable);
		assertNotNull(currentPrice);
		assertNotEquals("unknown", currentPrice);
		assertEquals("96.42", currentPrice);
		
		try {
			Float.parseFloat(currentPrice);
		} catch (NumberFormatException e) {
			fail();
		}
	}	
	
}
