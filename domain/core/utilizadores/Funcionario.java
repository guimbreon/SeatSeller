package domain.core.utilizadores;

import domain.core.lugares.Grelha;
import domain.api.INotificacaoReceiver;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;



public class Funcionario extends Utilizador implements PropertyChangeListener{
    private final Set<INotificacaoReceiver> receivers;
	private LocalTime st;
	private LocalTime end;
	
	public Funcionario(String username, String password, LocalTime st, LocalTime end) {
		super(username, password);
		this.st = st;
		this.end = end;
		this.receivers = new HashSet<>();
	}
	
	
	 /**
     * Associa o funcionário a uma grelha, adicionando-o como observador
     * e registando o receptor de notificações.
     * 
     * @param g Grela a associar.
     * @param notR Receptor de notificações a adicionar.
     */
	public void associarGrelha(Grelha g, INotificacaoReceiver notR) {
		g.addObserver(this);
		adicionaNotificationReceiver(notR);
	}

	
	  /**
     * Desassocia o funcionário de uma grelha, removendo-o dos observadores
     * e removendo o receptor de notificações.
     * 
     * @param g Grela a desassociar.
     * @param notR Receptor de notificações a remover.
     */
	public void desassociarGrelha(Grelha g, INotificacaoReceiver notR) {
		g.deleteObserver(this);
		removeNotificationReceiver(notR);
	}
	
	
    /**
     * Adiciona um receptor de notificações.
     * 
     * @param receiver Receptor a adicionar.
     */
	public void adicionaNotificationReceiver(INotificacaoReceiver receiver) {
		receivers.add(receiver);
	}
	
	
	 /**
     * Remove um receptor de notificações.
     * 
     * @param receiver Receptor a remover.
     */
	public void removeNotificationReceiver(INotificacaoReceiver receiver) {
	    receivers.remove(receiver);
	}

    /**
     * Método chamado quando uma propriedade observada sofre alteração.
     * Envia a notificação para todos os receivers registados,
     * caso o novo valor seja uma String com informação relevante.
     * 
     * @param evt Evento de alteração da propriedade.
     */
	 @Override
	    public void propertyChange(PropertyChangeEvent evt) {
	        Object newValue = evt.getNewValue();

	        if (newValue instanceof String) {
	            String infoLugar = (String) newValue;
	            for (INotificacaoReceiver receiver : receivers) {
	                receiver.notificar(infoLugar);
	            }
	        }
	    }
	
}
