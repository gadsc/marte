package br.com.nasa.server.teste.model;

import junit.framework.Assert;

import org.junit.Test;

import br.com.nasa.server.constants.ExceptionConstants;
import br.com.nasa.server.exception.PosicaoInvalidaException;
import br.com.nasa.server.model.Posicao;

/**
 * @author Gabz
 */
public class PosicaoTest {

	private static int POSICAO_VALIDA = 1;
	private static int POSICAO_INVALIDA = -1;

	@Test
	public void testePosicaoValida() {
		Posicao posicao = new Posicao(POSICAO_VALIDA);
		Assert.assertNotNull(posicao);
	}

	@Test
	public void testePosicaoInvalida() {
		try {
			new Posicao(POSICAO_INVALIDA);
		} catch (PosicaoInvalidaException exc) {
			Assert.assertEquals(ExceptionConstants.POSICAO_NEGATIVA,
					exc.getMessage());
		}
	}

	@Test
	public void testeAdicionarValido() {
		Posicao posicao = new Posicao(POSICAO_VALIDA);
		Assert.assertNotSame(posicao.getValor(), posicao.add(1, 5).getValor());
	}

	@Test
	public void testeAdicionarInvalido() {
		try {
			new Posicao(POSICAO_VALIDA).add(1, 1);
		} catch (PosicaoInvalidaException exc) {
			Assert.assertEquals(
					ExceptionConstants.LIMITE_MAXIMO_POSICAO_ATINGIDO,
					exc.getMessage());
		}
	}

	@Test
	public void testeSubtrairValido() {
		Posicao posicao = new Posicao(POSICAO_VALIDA);
		Assert.assertNotSame(posicao.getValor(), posicao.subtract(1, 0)
				.getValor());
	}

	@Test
	public void testeSubtrairInvalido() {
		try {
			new Posicao(POSICAO_VALIDA).subtract(2, 1);
		} catch (PosicaoInvalidaException exc) {
			Assert.assertEquals(
					ExceptionConstants.LIMITE_MINIMO_POSICAO_ATINGIDO,
					exc.getMessage());
		}
	}
}