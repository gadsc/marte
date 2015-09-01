package br.com.nasa.server.enums;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Gabz
 *
 */
@XmlRootElement
public enum DirecaoCardial {
	NORTH(0, "N"), WEST(1, "W"), SOUTH(2, "S"), EAST(3, "E");

	private int codigoDirecao;
	private String direcao;

	private DirecaoCardial(int codigoDirecao, String direcao) {
		this.codigoDirecao = codigoDirecao;
		this.direcao = direcao;
	}

	public int getCodigoDirecao() {
		return codigoDirecao;
	}

	public String getDirecao() {
		return direcao;
	}

	public static DirecaoCardial getPorCodigoDirecao(int codigoDirecao) {

		for (DirecaoCardial direcaoCardial : DirecaoCardial.values()) {
			if (direcaoCardial.codigoDirecao == codigoDirecao) {
				return direcaoCardial;
			}
		}

		return null;
	}
}