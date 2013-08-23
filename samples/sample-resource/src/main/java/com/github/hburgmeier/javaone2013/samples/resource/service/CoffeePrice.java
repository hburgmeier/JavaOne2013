package com.github.hburgmeier.javaone2013.samples.resource.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CoffeePrice {

	private String price;
	private String timestamp;
	
	public CoffeePrice()
	{
		
	}

	public CoffeePrice(String price, String timestamp) {
		super();
		this.price = price;
		this.timestamp = timestamp;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
