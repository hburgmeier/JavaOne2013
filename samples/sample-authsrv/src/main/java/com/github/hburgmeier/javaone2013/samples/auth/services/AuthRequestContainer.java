package com.github.hburgmeier.javaone2013.samples.auth.services;

import java.io.Serializable;
import java.util.Set;

import com.github.hburgmeier.jerseyoauth2.api.protocol.IAuthorizationRequest;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IRegisteredClientApp;

public class AuthRequestContainer implements Serializable {

	public static final String KEY = "AuthRequestContainer";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Set<String> scopes;
	protected IAuthorizationRequest originalRequest;
	protected IRegisteredClientApp clientApp;
	
	public AuthRequestContainer() { }
	
	public AuthRequestContainer(IAuthorizationRequest originalRequest, IRegisteredClientApp clientApp,
			Set<String> scopes) {
		super();
		this.originalRequest = originalRequest;
		this.clientApp = clientApp;
		this.scopes = scopes;
	}

	public Set<String> getScopes() {
		return scopes;
	}
	public void setScopes(Set<String> scopes) {
		this.scopes = scopes;
	}
	public IAuthorizationRequest getOriginalRequest() {
		return originalRequest;
	}
	public void setOriginalRequest(IAuthorizationRequest originalRequest) {
		this.originalRequest = originalRequest;
	}
	public IRegisteredClientApp getClientApp() {
		return clientApp;
	}
	public void setClientApp(IRegisteredClientApp clientApp) {
		this.clientApp = clientApp;
	}
	
	
	
}
