package br.com.nasa.server.rs.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;
import br.com.nasa.server.model.Planalto;

@XmlRootElement(name = "sondaRequest")
public class SondaRequest implements Serializable {
	private static final long serialVersionUID = -1729529959511228515L;

	private Planalto planalto;
	private int xInicialSonda;
	private int yInicialSonda;
	private DirecaoCardial direcaoInicial;
	private List<ComandoControleSonda> comandos;

	public Planalto getPlanalto() {
		return planalto;
	}

	public void setPlanalto(Planalto planalto) {
		this.planalto = planalto;
	}

	public int getxInicialSonda() {
		return xInicialSonda;
	}

	public void setxInicialSonda(int xInicialSonda) {
		this.xInicialSonda = xInicialSonda;
	}

	public int getyInicialSonda() {
		return yInicialSonda;
	}

	public void setyInicialSonda(int yInicialSonda) {
		this.yInicialSonda = yInicialSonda;
	}

	public DirecaoCardial getDirecaoInicial() {
		return direcaoInicial;
	}

	public void setDirecaoInicial(DirecaoCardial direcaoInicial) {
		this.direcaoInicial = direcaoInicial;
	}

	public List<ComandoControleSonda> getComandos() {
		return comandos;
	}

	public void setComandos(List<ComandoControleSonda> comandos) {
		this.comandos = comandos;
	}
}