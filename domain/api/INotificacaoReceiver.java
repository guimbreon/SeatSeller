package domain.api;


/**
 * Interface implementada pela camada de apresentacao para receber a 
 * notificacao de quando um lugar e' reservado.
 * 
 * Corresponde ao interface de Observer do padrao Observer.
 */
@FunctionalInterface
public interface INotificacaoReceiver {
	
	/**
	 * Recebe informacao sobre o lugar reservado e atua em
	 * conformidade
	 * @param infoLugar - a descricao do lugar reservado
	 */
	void notificar(String infoLugar);
}
