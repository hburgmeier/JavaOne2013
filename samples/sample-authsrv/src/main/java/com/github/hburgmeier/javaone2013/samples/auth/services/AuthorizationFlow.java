package com.github.hburgmeier.javaone2013.samples.auth.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.hburgmeier.jerseyoauth2.api.protocol.IAuthorizationRequest;
import com.github.hburgmeier.jerseyoauth2.api.protocol.IRefreshTokenRequest;
import com.github.hburgmeier.jerseyoauth2.api.user.IUser;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.IConfiguration;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.ScopeDescription;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IRegisteredClientApp;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.ui.AuthorizationFlowException;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.ui.IAuthorizationFlow;

public class AuthorizationFlow implements IAuthorizationFlow {

	private final IConfiguration configuration;
	
	@Inject
	public AuthorizationFlow(IConfiguration configuration)
	{
		this.configuration = configuration;
	}
	
	@Override
	public void startAuthorizationFlow(IUser user, IRegisteredClientApp clientApp, Set<String> scope, IAuthorizationRequest originalRequest, 
			HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws AuthorizationFlowException, ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		AuthRequestContainer container = new AuthRequestContainer(originalRequest, clientApp, scope);
		session.setAttribute(AuthRequestContainer.KEY, container);
		
		List<String> scopeDescs = new LinkedList<>();
		if (scope!=null)
		{
			Map<String, ScopeDescription> scopeMap = configuration.getScopeDescriptions();
			for (String scopeItem : scope)
			{
				String scopeDescription = scopeMap.get(scopeItem).getDescription();
				scopeDescs.add(scopeDescription);
			}
		}
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/oauth2/auth.jsp");
		request.setAttribute("clientApp", clientApp);
		request.setAttribute("scopeDesc", scopeDescs);
		
		requestDispatcher.forward(request, response);
	}

	@Override
	public void handleMissingUser(HttpServletRequest request,
			HttpServletResponse response, ServletContext servletContext)
			throws AuthorizationFlowException, ServletException, IOException {
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/error.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	public void handleInvalidClient(HttpServletRequest request,
			HttpServletResponse response, ServletContext servletContext)
			throws AuthorizationFlowException, ServletException, IOException {
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/error.jsp");
		requestDispatcher.forward(request, response);
	}
	
	@Override
	public void startScopeEnhancementFlow(IUser user, IRegisteredClientApp clientApp, Set<String> requestedScope,
			IRefreshTokenRequest refreshTokenRequest, HttpServletRequest request, HttpServletResponse response,
			ServletContext servletContext) throws AuthorizationFlowException {
		try {
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/error.jsp");
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			throw new AuthorizationFlowException(e);
		}
	}
	
}
