package br.com.nasa.server.exception;

/**
 * 
 * @author Gabz
 *
 */
public class PontoInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 3798846877669209701L;

	public PontoInvalidoException(final String message) {
		super(message);
	}
}