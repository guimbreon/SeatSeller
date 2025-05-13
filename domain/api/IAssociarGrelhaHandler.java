package domain.api;

/**
 * Handler do Caso de Uso UC9 - Associar grelha
 * Este handler so' estara' acessivel caso o utilizador autenticado seja 
 * funcionario
 */
public interface IAssociarGrelhaHandler {

	/**
	 * Associa uma dada grelha ao funcionario autenticado
	 * @param desig - Designacao da grelha a associar
	 * @param c - O receiver das notificacoes que serao enviadas ao
	 *            funcionario sempre que sao feitas reservas de lugares      
	 * @requires desig != null && c != null
	 * @ensures Se uma grelha com a designacao desig existir, o funcionario
	 *          autenticado passa a estar registado como seu observador
	 */
	void associarGrelha(String desig, INotificacaoReceiver c);

}
