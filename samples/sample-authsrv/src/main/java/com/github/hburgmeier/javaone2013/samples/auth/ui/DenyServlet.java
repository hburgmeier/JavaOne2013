package com.github.hburgmeier.javaone2013.samples.auth.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.hburgmeier.javaone2013.samples.auth.services.AuthRequestContainer;
import com.github.hburgmeier.jerseyoauth2.api.protocol.OAuth2ErrorCode;
import com.github.hburgmeier.jerseyoauth2.api.protocol.OAuth2ProtocolException;
import com.github.hburgmeier.jerseyoauth2.api.protocol.ResponseBuilderException;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.authorization.IAuthorizationService;
import com.google.inject.Singleton;

@Singleton
public class DenyServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final IAuthorizationService authorizationService;
	
	@Inject
	public DenyServlet(IAuthorizationService authorizationService) {
		super();
		this.authorizationService = authorizationService;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			AuthRequestContainer container = (AuthRequestContainer)session.getAttribute(AuthRequestContainer.KEY);
			if (container == null)
				throw new ServletException("Container is missing");
			
			String state = container.getOriginalRequest().getState();
			URI redirectUrl = new URI(container.getClientApp().getCallbackUrl());
			
			OAuth2ProtocolException error = new OAuth2ProtocolException(OAuth2ErrorCode.ACCESS_DENIED, state); 
			authorizationService.sendErrorResponse(error, response, redirectUrl );
		} catch (ResponseBuilderException | URISyntaxException e) {
			throw new ServletException(e);
		}
	}
}
