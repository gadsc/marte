package br.com.nasa.server.rs.exception;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;

/**
 * Mapper para recuperar as mensagens do bean validation no JAX-RS e exibi-las
 * para o usuario como resposta do serviço
 * 
 * @author Gabz
 *
 */
@Provider
public class ValidationExceptionMapper implements
		ExceptionMapper<ValidationException> {

	public Response toResponse(ValidationException exception) {
		if (exception instanceof ResteasyViolationException) {
			ResteasyViolationException restEasyException = (ResteasyViolationException) exception;
			ResteasyConstraintViolation violation = (ResteasyConstraintViolation) restEasyException
					.getParameterViolations().iterator().next();

			return Response.status(Status.BAD_REQUEST)
					.entity(violation.getMessage()).build();
		}

		return Response.status(Status.BAD_REQUEST)
				.entity(exception.getMessage()).build();
	}
}