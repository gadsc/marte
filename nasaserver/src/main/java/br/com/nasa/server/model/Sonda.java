package br.com.nasa.server.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.nasa.server.constants.ConstraintConstants;
import br.com.nasa.server.constants.ExceptionConstants;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;
import br.com.nasa.server.exception.NovaSondaException;
import br.com.nasa.server.exception.PlanaltoInvalidoException;

/**
 * Modelo que representa a Sonda e suas operações
 * 
 * @author Gabz
 *
 */
@XmlRootElement(name = "sonda")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sonda implements Serializable {
	private static final long serialVersionUID = 2799573443715829148L;

	@NotNull(message = ConstraintConstants.INFORME_PONTO_ATUAL_SONDA)
	private Ponto pontoAtual;

	@XmlTransient
	private Planalto planalto;

	@NotNull(message = ConstraintConstants.ADICIONE_LISTA_COMANDOS)
	private List<ComandoControleSonda> comandos;

	public Sonda() {

	}

	public Sonda(Ponto ponto) {
		this.pontoAtual = ponto;
	}

	public Sonda novaSonda(int x, int y, DirecaoCardial direcao,
			List<ComandoControleSonda> comandos) {
		try {
			if (this.planaltoValido() && this.sondaNoLimitePlanalto(x, y)) {
				this.pontoAtual = new Ponto().novoPonto(x, y, direcao);
			}
			this.setComandos(comandos);
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

	/**
	 * Método que move a Sonda no Planalto a partir de um comando
	 * 
	 * @param comando
	 * @return
	 */
	public Sonda moverSonda(ComandoControleSonda comando) {
		if (this.planaltoValido()) {
			this.pontoAtual.mover(comando, this.planalto);
		}
		return this;
	}

	public Sonda moverListaComandos(List<ComandoControleSonda> comandos) {
		this.setComandos(comandos);

		for (ComandoControleSonda comando : this.comandos) {
			this.moverSonda(comando);
		}

		return this;
	}

	public Sonda moverListaComandos() {
		return this.moverListaComandos(this.comandos);
	}

	/**
	 * @return posição atual da Sonda
	 */
	public String posicaoAtual() {
		return "x: " + pontoAtual.getX().getValor() + "\ty:"
				+ pontoAtual.getY().getValor() + "\tDireção: "
				+ pontoAtual.getDirecaoAtual().getDirecao();
	}

	public void setComandos(List<ComandoControleSonda> comandos) {
		if (comandos != null) {
			this.comandos = comandos;
		}
	}

	/**
	 * @return se o Planalto atrelado à Sonda é valido
	 */
	private boolean planaltoValido() {
		if (this.planalto == null) {
			throw new PlanaltoInvalidoException(
					ExceptionConstants.PLANALTO_INVALIDO);
		} else {
			return true;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return se a sonda está dentro do limite do planalto
	 */
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

	/**
	 * Método que calcula se a Sonda está dentro ou fora do limite do planalto
	 * 
	 * @param valor
	 * @param limiteMinimo
	 * @param limiteMaximo
	 * @return
	 */
	private boolean dentroDoLimite(int valor, int limiteMinimo, int limiteMaximo) {
		return valor >= limiteMinimo && valor <= limiteMaximo;
	}

	public Ponto getPontoAtual() {
		return pontoAtual;
	}

	public Planalto getPlanalto() {
		return planalto;
	}
}