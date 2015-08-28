package br.com.nasa.server.enums;

public enum ComandoControleSonda {
	LEFT("L"), RIGHT("R"), MOVE("M");
	
	private String codigoComando;

	private ComandoControleSonda(String codigoDirecao) {
		this.codigoComando = codigoDirecao;
	}

	public String getCodigoDirecao() {
		return codigoComando;
	}
}