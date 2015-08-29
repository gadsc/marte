package br.com.nasa.server.teste.entities;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import br.com.nasa.server.entities.Posicao;

/**
 * @author Gabz
 */
public class PosicaoTeste extends TestCase {

	private static int POSICAO_VALIDA = 1;
	private static int POSICAO_INVALIDA = -1;

	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testePosicaoValida() {
		Posicao posicao = new Posicao(POSICAO_VALIDA);
		Assert.assertNotNull(posicao);
	}

	@Test
	public void testePosicaoInalida() {
		try {
			new Posicao(POSICAO_INVALIDA);
		} catch (ArithmeticException exc) {
			Assert.assertEquals(
					"Não é possível criar um planalto com terreno negativo.",
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
		} catch (ArithmeticException exc) {
			Assert.assertEquals("Não é possível adicionar do ponto máximo.",
					exc.getMessage());
		}
	}
	
	@Test
	public void testeSubtrairValido() {
		Posicao posicao = new Posicao(POSICAO_VALIDA);
		Assert.assertNotSame(posicao.getValor(), posicao.subtract(1, 0).getValor());
	}

	@Test
	public void testeSubtrairInvalido() {
		try {
			new Posicao(POSICAO_VALIDA).subtract(2, 1);
		} catch (ArithmeticException exc) {
			Assert.assertEquals("Não é possível subtrair do ponto mínimo.",
					exc.getMessage());
		}
	}
}