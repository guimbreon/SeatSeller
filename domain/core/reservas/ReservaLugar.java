package domain.core.reservas;

import domain.core.lugares.Lugar;

public class ReservaLugar {
    private Lugar lugar;
    private LinhaReserva linhaReserva;
    
    public ReservaLugar(Lugar l, LinhaReserva lr) {
    	this.lugar = l;
    	this.linhaReserva = lr;
    }

    public Lugar getLugar() {
    	return this.lugar;
    }

    public LinhaReserva getLinhaReserva() {
    	return this.linhaReserva;
    }

}