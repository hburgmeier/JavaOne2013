package com.github.hburgmeier.javaone2013.samples.resource.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CoffeePrice {

	private String price;
	
	public CoffeePrice()
	{
		
	}

	public CoffeePrice(String price) {
		super();
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}
