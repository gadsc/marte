package br.com.nasa.server.teste.model;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import br.com.nasa.server.constants.ExceptionConstants;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;
import br.com.nasa.server.exception.NovaSondaException;
import br.com.nasa.server.model.Planalto;
import br.com.nasa.server.model.Sonda;

/**
 * @author Gabz
 */
public class SondaTest {
	@Test
	public void testeNovaSondaValida() {
		Sonda sonda = new Sonda()
				.pousarSondaNoPlanalto(new Planalto().novoPlanalto(5, 5))
				.novaSonda(
						1,
						2,
						DirecaoCardial.NORTH,
						Arrays.asList(new ComandoControleSonda[] { ComandoControleSonda.M }));
		Assert.assertNotNull(sonda);
	}

	@Test
	public void testeNovaSondaPontoInvalido() {
		try {
			new Sonda()
					.pousarSondaNoPlanalto(new Planalto().novoPlanalto(5, 5))
					.novaSonda(
							8,
							8,
							DirecaoCardial.NORTH,
							Arrays.asList(new ComandoControleSonda[] { ComandoControleSonda.M }));
		} catch (NovaSondaException exc) {
			Assert.assertEquals(ExceptionConstants.SONDA_FORA_LIMITE_PLANALTO,
					exc.getMessage());
		}
	}

	@Test
	public void testeNovaSondaSemPlanalto() {
		try {
			new Sonda()
					.novaSonda(
							1,
							5,
							DirecaoCardial.NORTH,
							Arrays.asList(new ComandoControleSonda[] { ComandoControleSonda.M }));
		} catch (NovaSondaException exc) {
			Assert.assertEquals(ExceptionConstants.SONDA_COM_PLANALTO_INVALIDO,
					exc.getMessage());
		}
	}

	@Test
	public void testeNovaSondaMovimentoYNorteValido() {
		Sonda sonda = new Sonda()
				.pousarSondaNoPlanalto(new Planalto().novoPlanalto(5, 5))
				.novaSonda(
						1,
						2,
						DirecaoCardial.NORTH,
						Arrays.asList(new ComandoControleSonda[] { ComandoControleSonda.M }));
		sonda.moverSonda(ComandoControleSonda.M);
		Assert.assertEquals(sonda.getPontoAtual().getY().getValor(), 3);
	}

	@Test
	public void testeNovaSondaMovimentoYSulValido() {
		Sonda sonda = new Sonda()
				.pousarSondaNoPlanalto(new Planalto().novoPlanalto(5, 5))
				.novaSonda(
						1,
						1,
						DirecaoCardial.SOUTH,
						Arrays.asList(new ComandoControleSonda[] { ComandoControleSonda.M }));
		sonda.moverSonda(ComandoControleSonda.M);
		Assert.assertEquals(sonda.getPontoAtual().getY().getValor(), 0);
	}

	@Test
	public void testeNovaSondaMovimentoXOesteValido() {
		Sonda sonda = new Sonda()
				.pousarSondaNoPlanalto(new Planalto().novoPlanalto(5, 5))
				.novaSonda(
						5,
						1,
						DirecaoCardial.WEST,
						Arrays.asList(new ComandoControleSonda[] { ComandoControleSonda.M }));
		sonda.moverSonda(ComandoControleSonda.M);
		Assert.assertEquals(sonda.getPontoAtual().getX().getValor(), 4);
	}

	@Test
	public void testeNovaSondaMovimentoXLesteValido() {
		Sonda sonda = new Sonda()
				.pousarSondaNoPlanalto(new Planalto().novoPlanalto(5, 5))
				.novaSonda(
						1,
						1,
						DirecaoCardial.EAST,
						Arrays.asList(new ComandoControleSonda[] { ComandoControleSonda.M }));
		sonda.moverListaComandos();
		Assert.assertEquals(sonda.getPontoAtual().getX().getValor(), 2);
	}

	@Test
	public void testeMovimentoSonda1() {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(
				new Planalto().novoPlanalto(5, 5)).novaSonda(
				1,
				2,
				DirecaoCardial.NORTH,
				Arrays.asList(new ComandoControleSonda[] {
						ComandoControleSonda.L, ComandoControleSonda.M,
						ComandoControleSonda.L, ComandoControleSonda.M,
						ComandoControleSonda.L, ComandoControleSonda.M,
						ComandoControleSonda.L, ComandoControleSonda.M,
						ComandoControleSonda.M }));

		sonda.moverListaComandos();
		Assert.assertEquals(sonda.getPontoAtual().getX().getValor(), 1);
		Assert.assertEquals(sonda.getPontoAtual().getY().getValor(), 3);
		Assert.assertEquals(sonda.getPontoAtual().getDirecaoAtual(),
				DirecaoCardial.NORTH);
	}

	@Test
	public void testeMovimentoSonda2() {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(
				new Planalto().novoPlanalto(5, 5)).novaSonda(
				3,
				3,
				DirecaoCardial.EAST,
				Arrays.asList(new ComandoControleSonda[] {
						ComandoControleSonda.M, ComandoControleSonda.M,
						ComandoControleSonda.R, ComandoControleSonda.M,
						ComandoControleSonda.M, ComandoControleSonda.R,
						ComandoControleSonda.M, ComandoControleSonda.R,
						ComandoControleSonda.R, ComandoControleSonda.M }));

		sonda.moverListaComandos();
		Assert.assertEquals(sonda.getPontoAtual().getX().getValor(), 5);
		Assert.assertEquals(sonda.getPontoAtual().getY().getValor(), 1);
		Assert.assertEquals(sonda.getPontoAtual().getDirecaoAtual(),
				DirecaoCardial.EAST);
	}
}