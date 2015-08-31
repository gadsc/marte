package br.com.nasa.server.teste.model;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import br.com.nasa.server.constants.ExceptionConstants;
import br.com.nasa.server.exception.PlanaltoInvalidoException;
import br.com.nasa.server.model.Planalto;

/**
 * @author Gabz
 */
public class PlanaltoTest extends TestCase {

	@Test
	public void testePlanaltoValido() {
		Assert.assertNotNull(new Planalto().novoPlanalto(5, 5));
	}

	@Test
	public void testePlanaltoInvalido() {
		try {
			new Planalto().novoPlanalto(-5, -5);
		} catch (PlanaltoInvalidoException exc) {
			Assert.assertEquals(ExceptionConstants.PLANALTO_NEGATIVO,
					exc.getMessage());
		}
	}
}