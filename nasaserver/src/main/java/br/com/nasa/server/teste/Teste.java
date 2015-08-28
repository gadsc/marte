package br.com.nasa.server.teste;

import br.com.nasa.server.entities.Planalto;
import br.com.nasa.server.entities.Sonda;
import br.com.nasa.server.enums.ComandoControleSonda;
import br.com.nasa.server.enums.DirecaoCardial;

public class Teste {

	public static void main(String[] args) {
		Planalto planalto = new Planalto().novoPlanalto(5, 5);
		Sonda sonda1 = new Sonda().novaSonda(1, 2, DirecaoCardial.NORTH)
				.pousarSondaNoPlanalto(planalto);
		Sonda sonda2 = new Sonda().novaSonda(3, 3, DirecaoCardial.EAST)
				.pousarSondaNoPlanalto(planalto);

		sonda1.moverSonda(ComandoControleSonda.LEFT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.LEFT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.LEFT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.LEFT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.MOVE);
		System.out.println("SONDA 1");
		System.out.println(sonda1.posicaoAtual());

		sonda2.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.RIGHT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.RIGHT)
				.moverSonda(ComandoControleSonda.MOVE)
				.moverSonda(ComandoControleSonda.RIGHT)
				.moverSonda(ComandoControleSonda.RIGHT)
				.moverSonda(ComandoControleSonda.MOVE);
		System.out.println("\nSONDA 2");
		System.out.println(sonda2.posicaoAtual());
	}
}