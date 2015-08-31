package br.com.nasa.server.exception;

public class PosicaoInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 4454399356715838770L;

	public PosicaoInvalidaException(final String message) {
		super(message);
	}
}