package br.com.nasa.server.model;

import java.io.Serializable;

import br.com.nasa.server.constants.ExceptionConstants;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;
import br.com.nasa.server.exception.NovaSondaException;
import br.com.nasa.server.exception.PlanaltoInvalidoException;

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
		try {
			if (this.planaltoValido() && this.sondaNoLimitePlanalto(x, y)) {
				this.pontoAtual = new Ponto().novoPonto(x, y, direcao);
			}
		} catch (PlanaltoInvalidoException exc) {
			throw new NovaSondaException(
					ExceptionConstants.SONDA_COM_PLANALTO_INVALIDO);
		}

		return this;
	}

	public Sonda pousarSondaNoPlanalto(Planalto planalto) {
		this.planalto = planalto;
		return this;
	}

	public Sonda moverSonda(ComandoControleSonda comando) {
		if (this.planaltoValido()) {
			this.pontoAtual.mover(comando, this.planalto);
		}
		return this;
	}

	public String posicaoAtual() {
		return "x: " + pontoAtual.getX().getValor() + "\ty:"
				+ pontoAtual.getY().getValor() + "\tDireçãoo: "
				+ pontoAtual.getDirecaoAtual().getDirecao();
	}

	private boolean planaltoValido() {
		if (this.planalto == null) {
			throw new PlanaltoInvalidoException(
					ExceptionConstants.PLANALTO_INVALIDO);
		} else {
			return true;
		}
	}

	private boolean sondaNoLimitePlanalto(int x, int y) {
		boolean isSondaNoLimite = (this.dentroDoLimite(x, planalto.getXMinimo()
				.getValor(), planalto.getXMaximo().getValor()))
				&& (this.dentroDoLimite(y, planalto.getYMinimo().getValor(),
						planalto.getYMaximo().getValor()));

		if (!isSondaNoLimite) {
			throw new NovaSondaException(
					ExceptionConstants.SONDA_FORA_LIMITE_PLANALTO);
		}

		return isSondaNoLimite;
	}

	private boolean dentroDoLimite(int valor, int limiteMinimo, int limiteMaximo) {
		return valor >= limiteMinimo && valor <= limiteMaximo;
	}

	public Ponto getPontoAtual() {
		return pontoAtual;
	}
}