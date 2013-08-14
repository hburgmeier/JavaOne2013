package com.github.hburgmeier.javaone2013.samples.auth.ui;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.hburgmeier.jerseyoauth2.api.protocol.OAuth2ErrorCode;
import com.github.hburgmeier.jerseyoauth2.api.protocol.OAuth2ProtocolException;
import com.github.hburgmeier.jerseyoauth2.api.protocol.ResponseBuilderException;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IAuthorizationService;
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
			String state = null;
			OAuth2ProtocolException error = new OAuth2ProtocolException(OAuth2ErrorCode.ACCESS_DENIED, state); 
			String redirectUrl = "http://www.yahoo.de/";
			authorizationService.sendErrorResponse(error, response, redirectUrl );
		} catch (ResponseBuilderException e) {
			throw new ServletException(e);
		}
	}
}
