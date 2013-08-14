package com.github.hburgmeier.javaone2013.samples.auth.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.hburgmeier.jerseyoauth2.api.protocol.OAuth2ProtocolException;
import com.github.hburgmeier.jerseyoauth2.api.protocol.ResponseBuilderException;
import com.github.hburgmeier.jerseyoauth2.api.types.ResponseType;
import com.github.hburgmeier.jerseyoauth2.api.user.IUser;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.ClientServiceException;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IAuthorizationService;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IAuthorizedClientApp;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IClientService;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IRegisteredClientApp;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.user.IUserService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AllowServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final IClientService clientService;
	private final IUserService userService;
	private final IAuthorizationService authorizationService;
	
	@Inject
	public AllowServlet(final IClientService clientService, final IUserService userService, final IAuthorizationService authorizationService) {
		super();
		this.clientService = clientService;
		this.userService = userService;
		this.authorizationService = authorizationService;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		IUser user = userService.getCurrentUser(request);
		
		String clientId = request.getParameter("client_id");
		IRegisteredClientApp clientApp = clientService.getRegisteredClient(clientId);
		
		String scopes = request.getParameter("scope");
		Set<String> allowedScopes = new HashSet<String>(Arrays.asList(scopes.split(" ")));
		
		ResponseType requestedResponseType = ResponseType.valueOf(request.getParameter("responseType"));
		
		try {
			IAuthorizedClientApp authorizedClient = clientService.authorizeClient(user, clientApp, allowedScopes);
			
			authorizationService.sendAuthorizationReponse(request, response, requestedResponseType, clientApp, authorizedClient, null);
		} catch (OAuth2ProtocolException | ResponseBuilderException | ClientServiceException e) {
			throw new ServletException(e);
		}
	}

}
