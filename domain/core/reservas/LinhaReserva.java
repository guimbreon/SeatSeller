package domain.core.reservas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import domain.core.lugares.Lugar;

public class LinhaReserva {


	private LocalDate data;
	private LocalTime hora;
    private List<ReservaLugar> reservaLugar;
    
	public LinhaReserva(LocalDate data, LocalTime hora) {
		this.data = data;
		this.hora = hora;
	}

	public double getSubtotal() {
		int subtotal = 0;

    	for(ReservaLugar rl: this.reservaLugar) {
    		subtotal += rl.getLugar().getPreco();
    	}
		return subtotal;
	}

	public void notificarGrelhas() {
    	for(ReservaLugar rl: this.reservaLugar) {
    		rl.getLugar().notificarGrelha();
    	}
	}

	public LocalDate getDate() {
		return this.data;
	}
	
	public LocalTime getTime() {
		return this.hora;
	}
	
	public void addLugar(Lugar l) {
		ReservaLugar rl = new ReservaLugar(l, this);
		this.reservaLugar.add(rl);
	}

}
