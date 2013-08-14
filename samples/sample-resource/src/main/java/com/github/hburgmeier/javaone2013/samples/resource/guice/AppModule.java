package com.github.hburgmeier.javaone2013.samples.resource.guice;

import java.util.HashMap;
import java.util.Map;

import org.ebaysf.web.cors.CORSFilter;

import com.github.hburgmeier.jerseyoauth2.rs.api.DefaultConfiguration;
import com.github.hburgmeier.jerseyoauth2.rs.api.IRSConfiguration;
import com.github.hburgmeier.jerseyoauth2.rs.api.token.IAccessTokenVerifier;
import com.github.hburgmeier.jerseyoauth2.rs.impl.filter.OAuth20FilterFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class AppModule extends JerseyServletModule {

	@Override
	protected void configureServlets() {
       bind(IRSConfiguration.class).to(DefaultConfiguration.class);
       bind(IAccessTokenVerifier.class).to(AccessTokenVerifier.class);
    	
       Map<String, String> params = new HashMap<String, String>();
       params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.github.hburgmeier.javaone2013.samples.resource.service");
//see http://java.net/jira/browse/JERSEY-630	            
       params.put(PackagesResourceConfig.FEATURE_DISABLE_WADL, "true");
       params.put(ResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES, OAuth20FilterFactory.class.getName());
       // Route all requests for rest resources through GuiceContainer
       serve("/rest/*").with(GuiceContainer.class, params);
       
       Map<String, String> corsFilterParams = new HashMap<>();
       corsFilterParams.put("cors.logging.enabled", "true");
       corsFilterParams.put(CORSFilter.PARAM_CORS_ALLOWED_ORIGINS, "*");
       corsFilterParams.put(CORSFilter.PARAM_CORS_ALLOWED_HEADERS, "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers, Authorization");
       filter("/*").through(new CORSFilter(), corsFilterParams);
	}
}
