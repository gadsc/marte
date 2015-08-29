package br.com.nasa.server.teste.entities;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import br.com.nasa.server.entities.Planalto;

/**
 * 
 * @author Gabz
 *
 */
public class PlanaltoTeste extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testePlanaltoValido() {
		Assert.assertNotNull(new Planalto().novoPlanalto(5, 5));
	}

	@Test
	public void testePlanaltoInvalido() {
		try {
			new Planalto().novoPlanalto(-5, -5);
		} catch (ArithmeticException exc) {
			Assert.assertEquals(
					"Não é possível criar um planalto com terreno negativo.",
					exc.getMessage());
		}
	}
}