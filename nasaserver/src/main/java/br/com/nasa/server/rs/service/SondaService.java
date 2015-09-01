package br.com.nasa.server.rs.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.nasa.server.constants.ConstraintConstants;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;
import br.com.nasa.server.model.Planalto;
import br.com.nasa.server.rs.request.SondaRequest;
import br.com.nasa.server.service.bean.SondaBean;

/**
 * Classe que expõe os serviços REST da Sonda
 * 
 * @author Gabz
 *
 */
@Path("/sonda")
public class SondaService implements Serializable {
	private static final long serialVersionUID = 2258435493236584123L;

	@Inject
	private SondaBean sondaBean;

	@Path("/get/mover/unica")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response moverGet(
			@NotNull(message = ConstraintConstants.X_MAXIMO_PLANALTO) @QueryParam("xMaximoPlanalto") Integer xPlanalto,
			@NotNull(message = ConstraintConstants.Y_MAXIMO_PLANALTO) @QueryParam("yMaximoPlanalto") Integer yPlanalto,
			@NotNull(message = ConstraintConstants.INFORME_X_INICIAL_SONDA) @Min(value = 0, message = ConstraintConstants.POSICAO_X_INICIAL_MENOR_0) @QueryParam("xInicialSonda") Integer xInicialSonda,
			@NotNull(message = ConstraintConstants.INFORME_Y_INICIAL_SONDA) @Min(value = 0, message = ConstraintConstants.POSICAO_Y_INICIAL_MENOR_0) @QueryParam("yInicialSonda") Integer yInicialSonda,
			@NotNull(message = ConstraintConstants.INFORME_DIRECAO_SONDA) @QueryParam("direcao") DirecaoCardial direcao,
			@NotNull(message = ConstraintConstants.ADICIONE_LISTA_COMANDOS) @QueryParam("comandos") List<ComandoControleSonda> comandos) {
		return Response.ok(
				sondaBean.executaSonda(
						new Planalto().novoPlanalto(xPlanalto, yPlanalto),
						xInicialSonda, yInicialSonda, direcao, comandos))
				.build();
	}

	@Path("/put/mover/lista")
	@PUT
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response moverPut(
			@NotNull(message = ConstraintConstants.INCLUA_SONDA_REQUISICAO) @NotEmpty(message = ConstraintConstants.INCLUA_SONDA_REQUISICAO) @Valid List<SondaRequest> sondasRequest) {
		Response response = null;

		try {
			response = Response.ok(sondaBean.executaSondas(sondasRequest))
					.build();
		} catch (RuntimeException exc) {
			response = Response.status(Status.BAD_REQUEST)
					.entity(exc.getMessage()).build();
		}

		return response;
	}
}