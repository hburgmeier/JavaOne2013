package com.github.hburgmeier.javaone2013.samples.auth.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.github.hburgmeier.jerseyoauth2.api.token.InvalidTokenException;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.token.IAccessTokenInfo;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.token.IAccessTokenStorageService;


@Path("/tokenInfo")
public class AccessTokenResource {

	private final IAccessTokenStorageService accessTokenStorageService;
	
	@Inject
	public AccessTokenResource(IAccessTokenStorageService accessTokenStorageService) {
		super();
		this.accessTokenStorageService = accessTokenStorageService;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public AccessTokenInfo getTokenInfo(@QueryParam("accessToken") String accessToken)
	{
		try {
			IAccessTokenInfo tokenInfo = accessTokenStorageService.getTokenInfoByAccessToken(accessToken);
			if (tokenInfo!=null)
				return new AccessTokenInfo(tokenInfo.getClientId(), tokenInfo.getAuthorizedScopes(), new User(tokenInfo.getUser().getName()));
			else
				throw new WebApplicationException(404);
		} catch (InvalidTokenException e) {
			return null;
		}
	}
	
}
