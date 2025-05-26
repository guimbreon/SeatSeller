package domain.core.reservas;

import java.util.UUID;


public class ReservaFactory {
    private CatalogoReservas catReservas;
	private static ReservaFactory INSTANCE = new ReservaFactory();
	

    private ReservaFactory() {
    }
    
    /**
     * Retorna a instância única da fábrica de reservas.
     * 
     * @return instância singleton de {@code ReservaFactory}
     */
    public static ReservaFactory getInstance() {
        return INSTANCE;
    }
    
    /**
     * Define o catálogo de reservas onde as reservas criadas serão registradas.
     * 
     * @param catReservas catálogo de reservas a ser associado
     */
    public void addCatReservas(CatalogoReservas catReservas) {
    	this.catReservas = catReservas;
    }


    /**
     * Cria uma nova reserva com um código único e a registra no catálogo.
     * 
     * @return uma nova instância de {@link Reserva} com código único
     */
    public Reserva getProximaReserva() {
        String codigo = novoCodigo();
        Reserva reserva = new Reserva(codigo); 
        catReservas.registrarReserva(reserva);
        return reserva;
    }

    
    /**
     * Gera um novo código único para uma reserva, garantindo que o código
     * não está presente no catálogo atual.
     * 
     * @return código único para uma nova reserva
     */
    public String novoCodigo() {
        String codigo;
        do {
            codigo = UUID.randomUUID().toString();
        } while (catReservas.getReserva(codigo) != null); 
        return codigo;
    }
}
