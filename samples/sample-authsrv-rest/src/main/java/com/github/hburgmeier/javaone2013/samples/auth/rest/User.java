package com.github.hburgmeier.javaone2013.samples.auth.rest;

import com.github.hburgmeier.jerseyoauth2.api.user.IUser;

public class User implements IUser {

	protected String userId;
	
	public User()
	{
	}
	
	public User(String userId) {
		super();
		this.userId = userId;
	}

	@Override
	public String getName() {
		return userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
