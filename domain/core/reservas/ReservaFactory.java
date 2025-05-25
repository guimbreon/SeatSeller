package domain.core.reservas;

import java.util.UUID;


public class ReservaFactory {
    private CatalogoReservas catReservas;
	private static ReservaFactory INSTANCE = new ReservaFactory();
	

    private ReservaFactory() {
    }
    
    public static ReservaFactory getInstance() {
        return INSTANCE;
    }
    
    public void addCatReservas(CatalogoReservas catReservas) {
    	this.catReservas = catReservas;
    }

    public Reserva getProximaReserva() {
        String codigo = novoCodigo();
        Reserva reserva = new Reserva(codigo); 
        catReservas.adicionarReserva(reserva);
        return reserva;
    }

    
    public String novoCodigo() {
        String codigo;
        do {
            codigo = UUID.randomUUID().toString();
        } while (catReservas.getReserva(codigo) != null); 
        return codigo;
    }
}
