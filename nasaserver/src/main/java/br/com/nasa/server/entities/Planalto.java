package br.com.nasa.server.entities;

import java.io.Serializable;

public class Planalto implements Serializable {
	private static final long serialVersionUID = -8414648699525607406L;
	private Posicao xMinimo;
	private Posicao xMaximo;
	private Posicao yMinimo;
	private Posicao yMaximo;

	public Planalto() {
		this.xMinimo = new Posicao(0);
		this.yMinimo = new Posicao(0);
	}

	public Planalto(Posicao xMaximo, Posicao yMaximo) {
		super();
		this.xMinimo = new Posicao(0);
		this.yMinimo = new Posicao(0);
		this.xMaximo = xMaximo;
		this.yMaximo = yMaximo;
	}

	public Planalto novoPlanalto(int xMaximo, int yMaximo) {
		try {
			return new Planalto(new Posicao(xMaximo), new Posicao(yMaximo));
		} catch (ArithmeticException exc) {
			throw new ArithmeticException(
					"Não é possível criar um planalto com terreno negativo.");
		}
	}

	public Posicao getXMinimo() {
		return xMinimo;
	}

	public Posicao getXMaximo() {
		return xMaximo;
	}

	public Posicao getYMinimo() {
		return yMinimo;
	}

	public Posicao getYMaximo() {
		return yMaximo;
	}
}