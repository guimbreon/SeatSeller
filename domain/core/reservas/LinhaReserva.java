package domain.core.reservas;

import java.util.List;

import domain.core.lugares.Lugar;

public class LinhaReserva {


	private String data;
	private String hora;
    private List<ReservaLugar> reservaLugar;
	
	public LinhaReserva(String data, String hora) {
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

	public String getDate() {
		return this.data;
	}
	
	public String getTime() {
		return this.hora;
	}
	
	public void addLugar(Lugar l) {
		ReservaLugar rl = new ReservaLugar(l, this);
		this.reservaLugar.add(rl);
	}

}
