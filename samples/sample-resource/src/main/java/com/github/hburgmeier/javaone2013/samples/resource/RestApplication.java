package com.github.hburgmeier.javaone2013.samples.resource;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.core.Application;

import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.github.hburgmeier.javaone2013.samples.resource.service.CoffeePriceService;
import com.github.hburgmeier.jerseyoauth2.api.protocol.IRequestFactory;
import com.github.hburgmeier.jerseyoauth2.protocol.impl.RequestFactory;
import com.github.hburgmeier.jerseyoauth2.rs.api.DefaultConfiguration;
import com.github.hburgmeier.jerseyoauth2.rs.api.IRSConfiguration;
import com.github.hburgmeier.jerseyoauth2.rs.api.token.IAccessTokenVerifier;
import com.github.hburgmeier.jerseyoauth2.rs.impl.rs2.filter.OAuth2FilterFeature;

public class RestApplication extends Application {
	
	@Inject
    public RestApplication(ServiceLocator serviceLocator) {
		DynamicConfiguration dc = Injections.getConfiguration(serviceLocator);
		
		Injections.addBinding(Injections.newBinder(DefaultConfiguration.class).to(IRSConfiguration.class),dc);
		Injections.addBinding(Injections.newBinder(AccessTokenVerifier.class).to(IAccessTokenVerifier.class), dc);
		Injections.addBinding(Injections.newBinder(RequestFactory.class).to(IRequestFactory.class), dc);
		
        dc.commit();		
	}	
	
	@Override
	public Set<Class<?>> getClasses() {
        Set<Class<?>> clazzes = new HashSet<Class<?>>();
        clazzes.add(CoffeePriceService.class);
        
        clazzes.add(JacksonFeature.class);
        clazzes.add(OAuth2FilterFeature.class);
        
        return clazzes;
	}

}
