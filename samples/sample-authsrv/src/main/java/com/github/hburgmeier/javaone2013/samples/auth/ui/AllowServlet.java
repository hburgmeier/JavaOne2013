package com.github.hburgmeier.javaone2013.samples.auth.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.hburgmeier.javaone2013.samples.auth.services.AuthRequestContainer;
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
		HttpSession session = request.getSession();		
		IUser user = userService.getCurrentUser(request);
		
		AuthRequestContainer container = (AuthRequestContainer)session.getAttribute(AuthRequestContainer.KEY);
		if (container == null)
			throw new ServletException("Container is missing");
		
		IRegisteredClientApp clientApp = container.getClientApp();
		Set<String> allowedScopes = container.getScopes();
		
		try {
			IAuthorizedClientApp authorizedClient = clientService.authorizeClient(user, clientApp, allowedScopes);
			
			authorizationService.sendAuthorizationReponse(request, response, container.getOriginalRequest(), clientApp, authorizedClient);
		} catch (OAuth2ProtocolException | ResponseBuilderException | ClientServiceException e) {
			throw new ServletException(e);
		}
	}

}
