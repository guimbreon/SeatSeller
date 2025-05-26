package domain.api.wrappers;

/**
 * Classe wrapper que "embrulha" 4 tipos de informacao sobre a disponibildade
 * de lugares em grelhas
 */
public class Combinacao {
	private String grelha;
	private String tipoDeLugar;
	private double preco;
	private int disponibilidade;
	
    /**
     * 
     * @param grelha - a designacao da grelha
     * @param tipo - o tipo de lugar disponivel
     * @param p - o preco desse tipo de lugar
     * @requires grelha != null && tipo != null
     * @ensures getGrelha().equals(grelha) && getTipoDeLugar().equals(tipo) &&
     *          getPreco()== preco && getDisponibilidade() == 1
     */
	public Combinacao(String grelha, String tipo, double p) {
		this.grelha = grelha;
		this.tipoDeLugar = tipo;
		this.preco = p;
		this.disponibilidade = 1;
	}
	/**
	 * Obtém a designação da grelha.
	 *
	 * @return a grelha
	 */
	public String getGrelha() {
		return grelha;
	}
	
	/**
	 * Obtém o tipo de lugar.
	 *
	 * @return o tipo de lugar
	 */
	public String getTipoDeLugar() {
		return tipoDeLugar;
	}

	/**
	 * Obtém o preço do lugar.
	 *
	 * @return o preço
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * Obtém o número de lugares disponíveis.
	 *
	 * @return a disponibilidade
	 */
	public int getDisponibilidade() {
		return disponibilidade;
	}
	
	/**
	 * @ensures getDisponibilidade() == \old(getDisponibilidade()) + 1
	 */
	public void aumentaDisponibilidade() {
		disponibilidade++;
	}
	
	/**
	 * Retorna uma representação textual da combinação.
	 *
	 * @return uma string formatada com os dados da combinação
	 */
	public String toString() {
		return this.getGrelha() + " | " + this.getTipoDeLugar() + " | " + 
	           this.getPreco() + " € | " + this.getDisponibilidade() + " livres";
	}
}
