package com.github.hburgmeier.javaone2013.samples.auth.services;

import java.util.HashMap;
import java.util.Map;

import com.github.hburgmeier.jerseyoauth2.authsrv.api.AbstractConfiguration;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.ScopeDescription;

public class Configuration extends AbstractConfiguration {

	protected final Map<String, ScopeDescription> scopeDescriptions = new HashMap<>();
	
	public Configuration()
	{
		ScopeDescription espresso = new ScopeDescription("espresso", "Get Coffee Prices");
		scopeDescriptions.put("espresso", espresso);
	}
	
	@Override
	public long getTokenExpiration() {
		return 3600;
	}

	@Override
	public Map<String, ScopeDescription> getScopeDescriptions() {
		return scopeDescriptions;
	}

	@Override
	public boolean getStrictSecurity() {
		return false;
	}

	@Override
	public boolean getEnableAuthorizationHeaderForClientAuth() {
		return false;
	}
	
	@Override
	public boolean getAllowScopeEnhancementWithRefreshToken() {
		return true;
	}
	
}
