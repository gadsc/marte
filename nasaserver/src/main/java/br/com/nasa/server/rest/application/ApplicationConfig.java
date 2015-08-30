package br.com.nasa.server.rest.application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.nasa.server.rest.exception.ValidationExceptionMapper;

/**
 * Classe que controla o rest e seus recursos da sua aplicação
 * 
 * @author Gabz
 *
 */
@ApplicationPath("/")
public class ApplicationConfig extends Application {
	private final Set<Class<?>> classes;

	public ApplicationConfig() {
		Set<Class<?>> c = new HashSet<Class<?>>();
		c.add(ValidationExceptionMapper.class);

		classes = Collections.unmodifiableSet(c);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
}
