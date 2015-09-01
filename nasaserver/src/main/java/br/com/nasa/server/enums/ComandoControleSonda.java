package br.com.nasa.server.enums;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Gabz
 *
 */
@XmlRootElement
public enum ComandoControleSonda {
	L("LEFT"),
	R("RRIGHT"),
	M("MOVE");

	private String codigoComando;

	private ComandoControleSonda(String codigoDirecao) {
		this.codigoComando = codigoDirecao;
	}

	public String getCodigoDirecao() {
		return codigoComando;
	}
}