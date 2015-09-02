package br.com.nasa.server.rs.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.nasa.server.constants.ConstraintConstants;
import br.com.nasa.server.model.Planalto;
import br.com.nasa.server.model.Sonda;

/**
 * Classe utilizada para Requests ao serviço de Sonda
 * 
 * @author Gabz
 *
 */
@XmlRootElement(name = "sondaRequest")
public class SondaRequest implements Serializable {
	private static final long serialVersionUID = -1729529959511228515L;

	@NotNull(message = ConstraintConstants.INFORME_X_Y_MAXIMO_PLANALTO)
	@Valid
	private Planalto planalto;

	@NotNull(message = ConstraintConstants.INCLUA_SONDA_REQUISICAO)
	@Valid
	private List<Sonda> sondas;

	public Planalto getPlanalto() {
		return planalto;
	}

	public void setPlanalto(Planalto planalto) {
		this.planalto = planalto;
	}

	public List<Sonda> getSondas() {
		return sondas;
	}

	public void setSondas(List<Sonda> sondas) {
		this.sondas = sondas;
	}
}