package domain.core.utilizadores;

import domain.core.lugares.Grelha;
import domain.api.INotificacaoReceiver;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;



public class Funcionario extends Utilizador 	{
    private final Set<INotificacaoReceiver> receivers;
	
	
	public Funcionario(String username, String password) {
		super(username, password);
		this.receivers = new HashSet<>();
	}
	
	public void associarGrelha(Grelha g, INotificacaoReceiver notR) {
		g.addPropertyChangeListener(this);
		adicionaNotificationReceiver(notR);
	}

	public void desassociarGrelha(Grelha g, INotificacaoReceiver notR) {
		g.removePropertyChangeListener(this);
		removeNotificationReceiver(notR);
	}
	
	public void adicionaNotificationReceiver(INotificacaoReceiver receiver) {
		receivers.add(receiver);
	}
	
	public void removeNotificationReceiver(INotificacaoReceiver receiver) {
	    receivers.remove(receiver);
	}
	
}
