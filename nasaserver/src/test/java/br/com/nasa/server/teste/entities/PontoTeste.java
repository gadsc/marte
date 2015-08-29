package br.com.nasa.server.teste.entities;

import java.security.InvalidParameterException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import br.com.nasa.server.entities.Planalto;
import br.com.nasa.server.entities.Ponto;
import br.com.nasa.server.entities.Posicao;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;

/**
 * @author Gabz
 */
public class PontoTeste extends TestCase {

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
		} catch (ArithmeticException exc) {
			Assert.assertEquals("Não é possível criar uma posição negativa.",
					exc.getMessage());
		}
	}

	@Test
	public void testePontoDirecaoInvalida() {
		try {
			new Ponto(new Posicao(1), new Posicao(1), null);
		} catch (InvalidParameterException exc) {
			Assert.assertEquals("Direção inválida!", exc.getMessage());
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
		} catch (ArithmeticException exc) {
			Assert.assertEquals("Não é possível adicionar do ponto máximo.",
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
		} catch (ArithmeticException exc) {
			Assert.assertEquals("Não é possível subtrair do ponto mínimo.",
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
		} catch (InvalidParameterException exc) {
			Assert.assertEquals("Comando não identificado!", exc.getMessage());
		}
	}

}