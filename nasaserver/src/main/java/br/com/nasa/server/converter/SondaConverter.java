package br.com.nasa.server.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.model.Sonda;
import br.com.nasa.server.rs.request.SondaRequest;

public class SondaConverter implements Serializable {
	private static final long serialVersionUID = -2163626647733471290L;

	public static List<Sonda> executaSondas(List<SondaRequest> requisicoes) {
		List<Sonda> sondas = new ArrayList<Sonda>();
		for (SondaRequest sondaRequest : requisicoes) {
			Sonda sonda = new Sonda().pousarSondaNoPlanalto(
					sondaRequest.getPlanalto()).novaSonda(
					sondaRequest.getxInicialSonda(),
					sondaRequest.getyInicialSonda(),
					sondaRequest.getDirecaoInicial());

			for (ComandoControleSonda comando : sondaRequest.getComandos()) {
				sonda.moverSonda(comando);
			}
			sondas.add(sonda);
		}

		return sondas;
	}
}