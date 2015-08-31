package br.com.nasa.server.rs.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.nasa.server.constants.ConstraintConstants;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;
import br.com.nasa.server.model.Planalto;

@XmlRootElement(name = "sondaRequest")
public class SondaRequest implements Serializable {
	private static final long serialVersionUID = -1729529959511228515L;

	@NotNull(message = ConstraintConstants.INFORME_X_Y_MAXIMO_PLANALTO)
	@Valid
	private Planalto planalto;
	@NotNull(message = ConstraintConstants.INFORME_X_INICIAL_SONDA)
	@Min(value = 0, message = ConstraintConstants.POSICAO_X_INICIAL_MENOR_0)
	private Integer xInicialSonda;
	@NotNull(message = ConstraintConstants.INFORME_Y_INICIAL_SONDA)
	@Min(value = 0, message = ConstraintConstants.POSICAO_Y_INICIAL_MENOR_0)
	private Integer yInicialSonda;
	@NotNull(message = ConstraintConstants.INFORME_DIRECAO_SONDA)
	private DirecaoCardial direcaoInicial;
	@NotNull(message = ConstraintConstants.ADICIONE_LISTA_COMANDOS)
	private List<ComandoControleSonda> comandos;

	public Planalto getPlanalto() {
		return planalto;
	}

	public void setPlanalto(Planalto planalto) {
		this.planalto = planalto;
	}

	public Integer getxInicialSonda() {
		return xInicialSonda;
	}

	public void setxInicialSonda(Integer xInicialSonda) {
		this.xInicialSonda = xInicialSonda;
	}

	public Integer getyInicialSonda() {
		return yInicialSonda;
	}

	public void setyInicialSonda(Integer yInicialSonda) {
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