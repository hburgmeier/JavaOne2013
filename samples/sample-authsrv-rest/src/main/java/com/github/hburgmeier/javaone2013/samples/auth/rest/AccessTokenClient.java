package com.github.hburgmeier.javaone2013.samples.auth.rest;

import javax.ws.rs.core.MediaType;

import com.github.hburgmeier.jerseyoauth2.api.token.IAccessTokenInfo;
import com.github.hburgmeier.jerseyoauth2.api.token.InvalidTokenException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class AccessTokenClient {
	
	protected Client restClient;
	protected String url;
	
	public AccessTokenClient(String url)
	{
		this.url = url;
		ClientConfig cc = new DefaultClientConfig();
		this.restClient = Client.create(cc);
	}
	
	public IAccessTokenInfo retrieveToken(String accessToken) throws InvalidTokenException {
		
		WebResource webResource = restClient.resource(url);
		ClientResponse response = webResource.queryParam("accessToken", accessToken).
				accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
		if (response.getStatus()!=200)
			return null;
		else {
			AccessTokenInfo tokenInfo = response.getEntity(AccessTokenInfo.class);
			return tokenInfo;
		}
	}
}
