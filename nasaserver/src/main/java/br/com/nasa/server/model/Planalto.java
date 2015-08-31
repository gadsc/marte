package br.com.nasa.server.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.nasa.server.constants.ExceptionConstants;
import br.com.nasa.server.exception.PlanaltoInvalidoException;
import br.com.nasa.server.exception.PosicaoInvalidaException;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Planalto implements Serializable {
	private static final long serialVersionUID = -8414648699525607406L;
	@XmlTransient
	private Posicao xMinimo;
	@NotNull
	private Posicao xMaximo;
	@XmlTransient
	private Posicao yMinimo;
	@NotNull
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
		} catch (PosicaoInvalidaException exc) {
			throw new PlanaltoInvalidoException(
					ExceptionConstants.PLANALTO_NEGATIVO);
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