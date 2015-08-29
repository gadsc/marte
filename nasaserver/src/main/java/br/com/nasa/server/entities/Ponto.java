package br.com.nasa.server.entities;

import java.io.Serializable;
import java.security.InvalidParameterException;

import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;

public class Ponto implements Serializable {
	private static final long serialVersionUID = -2222430550784957493L;

	private Posicao x;
	private Posicao y;
	private DirecaoCardial direcaoAtual;

	public Ponto mover(ComandoControleSonda comando, Planalto planalto) {
		if (comando == null) {
			throw new InvalidParameterException("Comando não identificado!");
		} else {
			switch (comando) {
			case LEFT:
				this.direcaoAtual = this.calculaProximaDirecao(
						DirecaoCardial.EAST, DirecaoCardial.NORTH, 1);
				break;
			case RIGHT:
				this.direcaoAtual = this.calculaProximaDirecao(
						DirecaoCardial.NORTH, DirecaoCardial.EAST, -1);
				break;
			case MOVE:
				if (this.isMovimentoY()) {
					this.calculaMovimento(DirecaoCardial.NORTH, this.y,
							planalto.getYMaximo().getValor(), planalto
									.getYMinimo().getValor());
				} else {
					this.calculaMovimento(DirecaoCardial.EAST, this.x, planalto
							.getXMaximo().getValor(), planalto.getXMinimo()
							.getValor());
				}
				break;
			default:
				throw new InvalidParameterException("Comando não identificado!");
			}
		}

		return this;
	}

	private boolean isMovimentoY() {
		return DirecaoCardial.NORTH.equals(this.direcaoAtual)
				|| DirecaoCardial.SOUTH.equals(this.direcaoAtual);
	}

	private Posicao calculaMovimento(DirecaoCardial direcaoMovimento,
			Posicao posicao, int maximoPermitido, int minimoPermitido) {
		return direcaoMovimento.equals(this.direcaoAtual) ? posicao.add(1,
				maximoPermitido) : posicao.subtract(1, minimoPermitido);
	}

	private DirecaoCardial calculaProximaDirecao(DirecaoCardial direcaoLimite,
			DirecaoCardial proximaDirecaoAposLimite, int moveProximaDirecao) {
		return direcaoLimite.equals(this.direcaoAtual) ? proximaDirecaoAposLimite
				: DirecaoCardial.getPorCodigoDirecao(this.direcaoAtual
						.getCodigoDirecao() + moveProximaDirecao);
	}

	public Ponto() {

	}

	public Ponto(Posicao x, Posicao y, DirecaoCardial direcao) {
		this.x = x;
		this.y = y;
		if (direcao != null) {
			this.direcaoAtual = direcao;
		} else {
			throw new InvalidParameterException("Direção inválida!");
		}
	}

	public Ponto novoPonto(int x, int y, DirecaoCardial direcao) {
		return new Ponto(new Posicao(x), new Posicao(y), direcao);
	}

	public Posicao getX() {
		return x;
	}

	public Posicao getY() {
		return y;
	}

	public DirecaoCardial getDirecaoAtual() {
		return direcaoAtual;
	}

}