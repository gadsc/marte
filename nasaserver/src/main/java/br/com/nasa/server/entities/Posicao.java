package br.com.nasa.server.entities;

import java.io.Serializable;

public class Posicao implements Serializable {
	private static final long serialVersionUID = 2425009801236102787L;
	private int valor;

	public Posicao() {

	}

	public Posicao(int valor) {
		if (valor < 0) {
			throw new ArithmeticException(
					"Não é possível criar um planalto com terreno negativo.");
		}
		this.valor = valor;
	}

	public Posicao add(int valor, int maximoPermitido) {
		if (this.valor < maximoPermitido) {
			this.valor += valor;
			return this;
		} else {
			throw new ArithmeticException(
					"Não é possível adicionar do ponto máximo.");
		}
	}

	public Posicao subtract(int valor, int minimoPermitido) {
		if (this.valor > minimoPermitido) {
			this.valor -= valor;
			return this;
		} else {
			throw new ArithmeticException(
					"Não é possível subtrair do ponto mínimo.");
		}
	}

	public int getValor() {
		return valor;
	}
}
