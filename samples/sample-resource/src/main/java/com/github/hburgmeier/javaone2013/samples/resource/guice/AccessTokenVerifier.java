package com.github.hburgmeier.javaone2013.samples.resource.guice;

import com.github.hburgmeier.javaone2013.samples.auth.rest.AccessTokenClient;
import com.github.hburgmeier.jerseyoauth2.api.token.IAccessTokenInfo;
import com.github.hburgmeier.jerseyoauth2.api.token.InvalidTokenException;
import com.github.hburgmeier.jerseyoauth2.rs.api.token.IAccessTokenVerifier;

public class AccessTokenVerifier implements IAccessTokenVerifier {

	protected AccessTokenClient client;
	
	public AccessTokenVerifier()
	{
		this.client = new AccessTokenClient("http://authserver-j1bof3861.rhcloud.com/rest/tokenInfo");
	}
	
	@Override
	public IAccessTokenInfo verifyAccessToken(String accessToken) throws InvalidTokenException {
		
		return client.retrieveToken(accessToken);
	}

}
