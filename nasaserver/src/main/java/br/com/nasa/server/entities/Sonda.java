package br.com.nasa.server.entities;

import java.io.Serializable;
import java.security.InvalidParameterException;

import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;

public class Sonda implements Serializable {
	private static final long serialVersionUID = 2799573443715829148L;

	private Ponto pontoAtual;
	private Planalto planalto;

	public Sonda() {

	}

	public Sonda(Ponto ponto) {
		this.pontoAtual = ponto;
	}

	public Sonda novaSonda(int x, int y, DirecaoCardial direcao) {
		return new Sonda(new Ponto().novoPonto(x, y, direcao));
	}

	public Sonda pousarSondaNoPlanalto(Planalto planalto) {
		this.planalto = planalto;
		return this;
	}

	public Sonda moverSonda(ComandoControleSonda comando) {
		if (this.planalto == null) {
			throw new InvalidParameterException(
					"Não é possível mover uma sonda antes dela pousar!");
		} else {
			this.pontoAtual.mover(comando, this.planalto);
			return this;
		}
	}

	public String posicaoAtual() {
		return "x: " + pontoAtual.getX().getValor() + "\ty:"
				+ pontoAtual.getY().getValor() + "\tDireçãoo: "
				+ pontoAtual.getDirecaoAtual().getDirecao();
	}
}