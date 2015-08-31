package br.com.nasa.server.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;
import br.com.nasa.server.model.Planalto;
import br.com.nasa.server.model.Sonda;
import br.com.nasa.server.rs.request.SondaRequest;

public class SondaBean implements Serializable {
	private static final long serialVersionUID = -2163626647733471290L;

	public List<Sonda> executaSondas(List<SondaRequest> requisicoes) {
		List<Sonda> sondas = new ArrayList<Sonda>();
		for (SondaRequest sondaRequest : requisicoes) {
			sondas.add(this.executaSonda(sondaRequest.getPlanalto(),
					sondaRequest.getxInicialSonda(),
					sondaRequest.getyInicialSonda(),
					sondaRequest.getDirecaoInicial(),
					sondaRequest.getComandos()));
		}

		return sondas;
	}

	public Sonda executaSonda(Planalto planalto, int xInicial, int yInicial,
			DirecaoCardial direcaoInicial, List<ComandoControleSonda> comandos) {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(planalto).novaSonda(
				xInicial, yInicial, direcaoInicial);

		for (ComandoControleSonda comando : comandos) {
			sonda.moverSonda(comando);
		}

		return sonda;
	}
}