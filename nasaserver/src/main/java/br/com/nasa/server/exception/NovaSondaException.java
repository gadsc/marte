package br.com.nasa.server.exception;

public class NovaSondaException extends RuntimeException {
	private static final long serialVersionUID = 7244774946476149009L;

	public NovaSondaException(final String message) {
		super(message);
	}
}