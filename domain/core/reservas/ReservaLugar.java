package domain.core.reservas;

import java.util.Optional;

import domain.core.lugares.Lugar;

public class ReservaLugar {
    private Lugar lugar;
    private LinhaReserva linhaReserva;
    
    /**
     * Construtor que cria uma reserva associando um lugar a uma linha de reserva.
     * 
     * @param lug o lugar reservado
     * @param lr a linha de reserva associada a esta reserva
     */
    public ReservaLugar(Lugar lug, LinhaReserva lr) {
    	this.lugar = lug;
    	this.linhaReserva = lr;
    }
    
    /**
     * Retorna o lugar reservado.
     * 
     * @return o {@link Lugar} associado a esta reserva
     */
    public Lugar getLugar() {
    	return this.lugar;
    }

    /**
     * Retorna a linha de reserva associada.
     * 
     * @return a {@link LinhaReserva} associada a esta reserva
     */
    public LinhaReserva getLinhaReserva() {
    	return this.linhaReserva;
    }

}