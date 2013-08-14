package com.github.hburgmeier.javaone2013.samples.auth.rest;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.github.hburgmeier.jerseyoauth2.api.token.IAccessTokenInfo;

@XmlRootElement
public class AccessTokenInfo implements IAccessTokenInfo {
	
	private Set<String> authorizedScopes;
	private User user;
	private String clientId;
	
	public AccessTokenInfo(String clientId, Set<String> authorizedScopes, User user) {
		this.clientId = clientId;
		this.authorizedScopes = authorizedScopes;
		this.user = user;
	}
	
	public AccessTokenInfo()
	{
	}

	public Set<String> getAuthorizedScopes() {
		return authorizedScopes;
	}

	public void setAuthorizedScopes(Set<String> authorizedScopes) {
		this.authorizedScopes = authorizedScopes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
