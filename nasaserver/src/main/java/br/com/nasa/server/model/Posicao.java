package br.com.nasa.server.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.nasa.server.constants.ExceptionConstants;
import br.com.nasa.server.exception.PosicaoInvalidaException;

/**
 * Modelo que representa uma Posicao
 * 
 * @author Gabz
 *
 */
@XmlRootElement
public class Posicao implements Serializable {
	private static final long serialVersionUID = 2425009801236102787L;
	@Min(value=0, message=ExceptionConstants.POSICAO_NEGATIVA)
	private int valor;

	public Posicao() {

	}

	public Posicao(int valor) {
		if (valor < 0) {
			throw new PosicaoInvalidaException(
					ExceptionConstants.POSICAO_NEGATIVA);
		}
		this.valor = valor;
	}

	public Posicao add(int valor, int maximoPermitido) {
		if (this.valor <= maximoPermitido) {
			this.valor += valor;
			return this;
		} else {
			throw new PosicaoInvalidaException(
					ExceptionConstants.LIMITE_MAXIMO_POSICAO_ATINGIDO);
		}
	}

	public Posicao subtract(int valor, int minimoPermitido) {
		if (this.valor >= minimoPermitido) {
			this.valor -= valor;
			return this;
		} else {
			throw new PosicaoInvalidaException(
					ExceptionConstants.LIMITE_MINIMO_POSICAO_ATINGIDO);
		}
	}

	public int getValor() {
		return valor;
	}
}
