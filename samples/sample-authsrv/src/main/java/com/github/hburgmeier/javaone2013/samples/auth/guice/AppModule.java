package com.github.hburgmeier.javaone2013.samples.auth.guice;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import com.github.hburgmeier.javaone2013.samples.auth.services.AuthorizationFlow;
import com.github.hburgmeier.javaone2013.samples.auth.services.Configuration;
import com.github.hburgmeier.javaone2013.samples.auth.ui.AllowServlet;
import com.github.hburgmeier.javaone2013.samples.auth.ui.DenyServlet;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.IConfiguration;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IClientIdGenerator;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.token.ITokenGenerator;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.ui.IAuthorizationFlow;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.user.IUserService;
import com.github.hburgmeier.jerseyoauth2.authsrv.impl.authorize.AuthorizationServlet;
import com.github.hburgmeier.jerseyoauth2.authsrv.impl.authorize.IssueAccessTokenServlet;
import com.github.hburgmeier.jerseyoauth2.authsrv.impl.services.DefaultPrincipalUserService;
import com.github.hburgmeier.jerseyoauth2.authsrv.impl.services.MD5TokenGenerator;
import com.github.hburgmeier.jerseyoauth2.authsrv.impl.services.UUIDClientIdGenerator;
import com.github.hburgmeier.jerseyoauth2.authsrv.jpa.guice.DefaultPersistenceProvider;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class AppModule extends JerseyServletModule {

	@Override
	protected void configureServlets() {
		bind(ITokenGenerator.class).to(MD5TokenGenerator.class);
		bind(IClientIdGenerator.class).to(UUIDClientIdGenerator.class);
		bind(IConfiguration.class).to(Configuration.class);
		bind(IUserService.class).to(DefaultPrincipalUserService.class);
		bind(EntityManagerFactory.class).toProvider(new DefaultPersistenceProvider("org.hibernate.dialect.PostgreSQLDialect"));
		bind(IAuthorizationFlow.class).to(AuthorizationFlow.class);
		
    	serve("/oauth2/authorize").with(AuthorizationServlet.class);
    	serve("/oauth2/allow").with(AllowServlet.class);
    	serve("/oauth2/denied").with(DenyServlet.class);
    	serve("/oauth2/accessToken").with(IssueAccessTokenServlet.class);
    	
        Map<String, String> params = new HashMap<String, String>();
        params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.github.hburgmeier.javaone2013.samples.auth.rest");
 //see http://java.net/jira/browse/JERSEY-630	            
        params.put(PackagesResourceConfig.FEATURE_DISABLE_WADL, "true");
 // Route all requests for rest resources through GuiceContainer
        serve("/rest/*").with(GuiceContainer.class, params);
    	
	}

}
