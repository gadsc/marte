package br.com.nasa.server.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.nasa.server.model.Planalto;
import br.com.nasa.server.model.Sonda;
import br.com.nasa.server.rs.request.SondaRequest;

/**
 * Classe que executa a Sonda, movimenta, cria e relaciona ela à um planalto
 * 
 * @author Gabz
 *
 */
public class SondaBean implements Serializable {
	private static final long serialVersionUID = -2163626647733471290L;

	public List<Sonda> moveSondas(SondaRequest requisicoes) {
		List<Sonda> sondas = new ArrayList<Sonda>();
		for (Sonda sondaRequest : requisicoes.getSondas()) {
			sondas.add(this.moveSonda(requisicoes.getPlanalto(), sondaRequest));
		}

		return sondas;
	}

	public Sonda moveSonda(Planalto planalto, Sonda sonda) {
		return sonda.pousarSondaNoPlanalto(planalto).moverListaComandos();
	}
}