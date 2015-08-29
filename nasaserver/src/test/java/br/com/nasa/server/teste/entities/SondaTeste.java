package br.com.nasa.server.teste.entities;

import java.security.InvalidParameterException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import br.com.nasa.server.entities.Planalto;
import br.com.nasa.server.entities.Sonda;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;

/**
 * @author Gabz
 */
public class SondaTeste extends TestCase {
	@Test
	public void testeNovaSondaValida() {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(
				new Planalto().novoPlanalto(5, 5)).novaSonda(1, 2,
				DirecaoCardial.NORTH);
		Assert.assertNotNull(sonda);
	}

	@Test
	public void testeNovaSondaPontoInvalido() {
		try {
			new Sonda()
					.pousarSondaNoPlanalto(new Planalto().novoPlanalto(5, 5))
					.novaSonda(8, 8, DirecaoCardial.NORTH);
		} catch (IllegalArgumentException exc) {
			Assert.assertEquals(
					"Impossível adicionar uma sonda fora do limite do planalto!",
					exc.getMessage());
		}
	}

	@Test
	public void testeNovaSondaSemPlanalto() {
		try {
			new Sonda().novaSonda(1, 5, DirecaoCardial.NORTH);
		} catch (InvalidParameterException exc) {
			Assert.assertEquals(
					"Não é possível criar uma nova sonda em um planalto inválido!",
					exc.getMessage());
		}
	}

	@Test
	public void testeNovaSondaMovimentoYNorteValido() {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(
				new Planalto().novoPlanalto(5, 5)).novaSonda(1, 2,
				DirecaoCardial.NORTH);
		sonda.moverSonda(ComandoControleSonda.MOVE);
		Assert.assertEquals(sonda.getPontoAtual().getY().getValor(), 3);
	}

	@Test
	public void testeNovaSondaMovimentoYSulValido() {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(
				new Planalto().novoPlanalto(5, 5)).novaSonda(1, 1,
				DirecaoCardial.SOUTH);
		sonda.moverSonda(ComandoControleSonda.MOVE);
		Assert.assertEquals(sonda.getPontoAtual().getY().getValor(), 0);
	}

	@Test
	public void testeNovaSondaMovimentoXOesteValido() {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(
				new Planalto().novoPlanalto(5, 5)).novaSonda(5, 1,
				DirecaoCardial.WEST);
		sonda.moverSonda(ComandoControleSonda.MOVE);
		Assert.assertEquals(sonda.getPontoAtual().getX().getValor(), 4);
	}

	@Test
	public void testeNovaSondaMovimentoXLesteValido() {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(
				new Planalto().novoPlanalto(5, 5)).novaSonda(1, 1,
				DirecaoCardial.EAST);
		sonda.moverSonda(ComandoControleSonda.MOVE);
		Assert.assertEquals(sonda.getPontoAtual().getX().getValor(), 2);
	}

	@Test
	public void testeMovimentoSonda1() {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(
				new Planalto().novoPlanalto(5, 5)).novaSonda(1, 2,
				DirecaoCardial.NORTH);

		sonda.moverSonda(ComandoControleSonda.LEFT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.LEFT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.LEFT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.LEFT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.MOVE);
		Assert.assertEquals(sonda.getPontoAtual().getX().getValor(), 1);
		Assert.assertEquals(sonda.getPontoAtual().getY().getValor(), 3);
		Assert.assertEquals(sonda.getPontoAtual().getDirecaoAtual(),
				DirecaoCardial.NORTH);
	}

	@Test
	public void testeMovimentoSonda2() {
		Sonda sonda = new Sonda().pousarSondaNoPlanalto(
				new Planalto().novoPlanalto(5, 5)).novaSonda(3, 3,
				DirecaoCardial.EAST);

		sonda.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.RIGHT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.RIGHT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.RIGHT)
				.moverSonda(ComandoControleSonda.RIGHT)
				.moverSonda(ComandoControleSonda.MOVE);
		Assert.assertEquals(sonda.getPontoAtual().getX().getValor(), 5);
		Assert.assertEquals(sonda.getPontoAtual().getY().getValor(), 1);
		Assert.assertEquals(sonda.getPontoAtual().getDirecaoAtual(),
				DirecaoCardial.EAST);
	}

}