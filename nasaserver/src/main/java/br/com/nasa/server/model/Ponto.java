package br.com.nasa.server.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.nasa.server.constants.ExceptionConstants;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;
import br.com.nasa.server.exception.PontoInvalidoException;

@XmlRootElement
public class Ponto implements Serializable {
	private static final long serialVersionUID = -2222430550784957493L;

	private Posicao x;
	private Posicao y;
	private DirecaoCardial direcaoAtual;

	public Ponto mover(ComandoControleSonda comando, Planalto planalto) {
		if (comando == null) {
			throw new PontoInvalidoException(
					ExceptionConstants.COMANDO_NAO_IDENTIFICADO);
		} else {
			switch (comando) {
			case L:
				this.direcaoAtual = this.calculaProximaDirecao(
						DirecaoCardial.EAST, DirecaoCardial.NORTH, 1);
				break;
			case R:
				this.direcaoAtual = this.calculaProximaDirecao(
						DirecaoCardial.NORTH, DirecaoCardial.EAST, -1);
				break;
			case M:
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
				throw new PontoInvalidoException(
						ExceptionConstants.COMANDO_NAO_IDENTIFICADO);
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
			throw new PontoInvalidoException(
					ExceptionConstants.DIRECAO_INVALIDA);
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