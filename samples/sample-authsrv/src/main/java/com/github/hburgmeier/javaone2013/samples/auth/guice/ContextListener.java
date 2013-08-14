package com.github.hburgmeier.javaone2013.samples.auth.guice;

import java.util.Arrays;
import java.util.Collection;

import com.github.hburgmeier.jerseyoauth2.authsrv.impl.guice.AuthorizationServerModule;
import com.github.hburgmeier.jerseyoauth2.authsrv.jpa.guice.PersistenceModule;
import com.github.hburgmeier.jerseyoauth2.guice.utils.BaseGuiceServletContextListener;
import com.google.inject.Module;

public class ContextListener extends BaseGuiceServletContextListener {

	@Override
	protected Collection<? extends Module> getApplicationModules() {
		return Arrays.asList(new AuthorizationServerModule(),
				new PersistenceModule(),
				new AppModule());
	}

}
