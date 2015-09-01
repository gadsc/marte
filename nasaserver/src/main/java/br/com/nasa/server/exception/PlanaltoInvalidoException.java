package br.com.nasa.server.exception;

/**
 * 
 * @author Gabz
 *
 */
public class PlanaltoInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 3092866401699926181L;
	
	public PlanaltoInvalidoException(final String message) {
		super(message);
	}
}