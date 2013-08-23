package com.github.hburgmeier.javaone2013.samples.coffee;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class CoffeeReader {

	private static final String PRICE_URL = "http://www.ico.org/prices/pr_files/sheet001.htm";
	private static final long DAY = 25*60*60*1000l;
	
	public String getCurrentPrice() throws CoffeeReadException
	{
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(PRICE_URL);

			HttpResponse response1 = httpclient.execute(httpGet);
			HttpEntity entity1 = response1.getEntity();
			try {
			    String priceTable = EntityUtils.toString(entity1);
			    return parseHtmlTable(priceTable);
			} finally {
				EntityUtils.consume(entity1);
			}			
		} catch (IOException e) {
			throw new CoffeeReadException(e);
		}		
	}

	protected String parseHtmlTable(String priceTable) {
		String currentDate = getCurrentDate();
		
		Pattern matcher = Pattern.compile(currentDate+"</td>[^<]*<td[^>]*>(\\d+\\.\\d+)");
		Matcher match = matcher.matcher(priceTable);
		if (match.find())
		{
			return match.group(1);
		} else
			return "unknown";
	}
	
	protected String getCurrentDate()
	{
		DateFormat format = new SimpleDateFormat("dd-MMM");
		Date today = new Date(System.currentTimeMillis() - DAY);
		return format.format(today);
	}
	
}
