package domain.api;

/**
 * Handler do Caso de Uso UC3 - Criar Tipo de lugar
 * Este handler so' estara' acessivel caso o utilizador autenticado seja 
 * administrador
 */
public interface ICriarTipoDeLugarHandler {
	
	/**
	 * 
	 * @param desig - Designacao do novo tipo de lugar
	 * @param desc - Descricao detalhada do tipo de lugar
	 * @param preco - preco do novo tipo de lugar
	 * @param padrao - se true e' tipo de lugar padrao
	 */
	void criarTipoDeLugar(String desig, String desc, double preco, boolean padrao);
}
