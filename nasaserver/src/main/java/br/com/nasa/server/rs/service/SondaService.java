package br.com.nasa.server.rs.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.nasa.server.converter.SondaConverter;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;
import br.com.nasa.server.model.Planalto;
import br.com.nasa.server.model.Sonda;
import br.com.nasa.server.rs.request.SondaRequest;

@Path("/sonda")
public class SondaService implements Serializable {
	private static final long serialVersionUID = 2258435493236584123L;
	
	@PostConstruct
	public void init() {
		
	}

	@Path("/get/mover")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response moverGet(@QueryParam("xMaximoPlanalto") int xPlanalto,
			@QueryParam("yMaximoPlanalto") int yPlanalto,
			@QueryParam("xInicialSonda") int xInicialSonda,
			@QueryParam("yInicialSonda") int yInicialSonda,
			@QueryParam("direcao") DirecaoCardial direcao,
			@QueryParam("comandos") List<ComandoControleSonda> comandos) {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(
				new Planalto().novoPlanalto(xPlanalto, yPlanalto)).novaSonda(
				xInicialSonda, yInicialSonda, direcao);

		for (ComandoControleSonda comando : comandos) {
			sonda.moverSonda(comando);
		}

		return Response.ok(sonda).build();
	}

	@Path("/put/mover")
	@PUT
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response moverPut(List<SondaRequest> sondasRequest) {
		return Response.ok(SondaConverter.executaSondas(sondasRequest))
				.build();
	}
}