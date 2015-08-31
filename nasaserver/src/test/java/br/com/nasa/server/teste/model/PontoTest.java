package br.com.nasa.server.teste.model;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import br.com.nasa.server.constants.ExceptionConstants;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;
import br.com.nasa.server.exception.PontoInvalidoException;
import br.com.nasa.server.exception.PosicaoInvalidaException;
import br.com.nasa.server.model.Planalto;
import br.com.nasa.server.model.Ponto;
import br.com.nasa.server.model.Posicao;

/**
 * @author Gabz
 */
public class PontoTest extends TestCase {

	@Test
	public void testePontoValido() {
		Ponto ponto = new Ponto(new Posicao(1), new Posicao(1),
				DirecaoCardial.NORTH);
		Assert.assertNotNull(ponto);
	}

	@Test
	public void testePontoPosicaoInvalida() {
		try {
			new Ponto(new Posicao(-1), new Posicao(1), DirecaoCardial.NORTH);
		} catch (PosicaoInvalidaException exc) {
			Assert.assertEquals(ExceptionConstants.POSICAO_NEGATIVA,
					exc.getMessage());
		}
	}

	@Test
	public void testePontoDirecaoInvalida() {
		try {
			new Ponto(new Posicao(1), new Posicao(1), null);
		} catch (PontoInvalidoException exc) {
			Assert.assertEquals(ExceptionConstants.DIRECAO_INVALIDA,
					exc.getMessage());
		}
	}

	@Test
	public void testeMoverPontoValido() {
		Ponto ponto = new Ponto(new Posicao(1), new Posicao(3),
				DirecaoCardial.NORTH);
		Assert.assertNotNull(ponto.mover(ComandoControleSonda.MOVE,
				new Planalto().novoPlanalto(5, 5)));
	}

	@Test
	public void testeMoverPontoInvalidoMaximo() {
		Ponto ponto = null;
		try {
			ponto = new Ponto(new Posicao(5), new Posicao(5),
					DirecaoCardial.NORTH);
			Assert.assertNotNull(ponto.mover(ComandoControleSonda.MOVE,
					new Planalto().novoPlanalto(5, 5)));
		} catch (PosicaoInvalidaException exc) {
			Assert.assertEquals(
					ExceptionConstants.LIMITE_MAXIMO_POSICAO_ATINGIDO,
					exc.getMessage());
		}
	}

	@Test
	public void testeMoverPontoInvalidoMinimo() {
		Ponto ponto = null;
		try {
			ponto = new Ponto(new Posicao(0), new Posicao(0),
					DirecaoCardial.SOUTH);
			Assert.assertNotNull(ponto.mover(ComandoControleSonda.MOVE,
					new Planalto().novoPlanalto(5, 5)));
		} catch (PosicaoInvalidaException exc) {
			Assert.assertEquals(
					ExceptionConstants.LIMITE_MINIMO_POSICAO_ATINGIDO,
					exc.getMessage());
		}
	}

	@Test
	public void testeComandoInvalido() {
		Ponto ponto = null;
		try {
			ponto = new Ponto(new Posicao(1), new Posicao(3),
					DirecaoCardial.NORTH);
			Assert.assertNotNull(ponto.mover(null,
					new Planalto().novoPlanalto(5, 5)));
		} catch (PontoInvalidoException exc) {
			Assert.assertEquals(ExceptionConstants.COMANDO_NAO_IDENTIFICADO,
					exc.getMessage());
		}
	}

}