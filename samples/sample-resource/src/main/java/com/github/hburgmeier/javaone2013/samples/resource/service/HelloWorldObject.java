package com.github.hburgmeier.javaone2013.samples.resource.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloWorldObject {

	protected String text;

	public HelloWorldObject()
	{
	}
	
	public HelloWorldObject(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
