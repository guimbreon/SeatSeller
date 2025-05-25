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
	
	public void associarGrelha(Grelha g, INotificacaoReceiver notR) {
		g.addObserver(this);
		adicionaNotificationReceiver(notR);
	}

	public void desassociarGrelha(Grelha g, INotificacaoReceiver notR) {
		g.deleteObserver(this);
		removeNotificationReceiver(notR);
	}
	
	public void adicionaNotificationReceiver(INotificacaoReceiver receiver) {
		receivers.add(receiver);
	}
	
	public void removeNotificationReceiver(INotificacaoReceiver receiver) {
	    receivers.remove(receiver);
	}

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
