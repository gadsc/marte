package br.com.nasa.server.constants;

/**
 * Constantes das exceptions
 * 
 * @author Gabz
 *
 */
public class ExceptionConstants {
	public static final String PLANALTO_NEGATIVO = "Não é possível criar um planalto com terreno negativo.";
	public static final String PLANALTO_INVALIDO = "Planalto inválido!";
	public static final String POSICAO_NEGATIVA = "Não é possível criar uma posição negativa.";
	public static final String LIMITE_MAXIMO_POSICAO_ATINGIDO = "Não é possível adicionar pois ponto máximo já foi atingido.";
	public static final String LIMITE_MINIMO_POSICAO_ATINGIDO = "Não é possível subtrair pois o ponto mínimo já foi atingido.";
	public static final String COMANDO_NAO_IDENTIFICADO = "Comando não identificado!";
	public static final String DIRECAO_INVALIDA = "Direção inválida!";
	public static final String SONDA_FORA_LIMITE_PLANALTO = "Impossível adicionar uma sonda fora do limite do planalto!";
	public static final String SONDA_COM_PLANALTO_INVALIDO = "Não é possível criar uma nova sonda em um planalto inválido!";
}
