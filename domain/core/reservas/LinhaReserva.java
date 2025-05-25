package domain.core.reservas;

import domain.core.lugares.Lugar;

public class LinhaReserva {


	private String data;
	private String hora;
	private Lugar lugar;
	
	public LinhaReserva(String data, String hora) {
		this.data = data;
		this.hora = hora;
	}

	public double getSubtotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void notificarGrelhas() {
		// TODO Auto-generated method stub
		
	}

	public String getDate() {
		return this.data;
	}
	
	public String getTime() {
		return this.hora;
	}
	
	public void addLugar(Lugar l) {
		this.lugar = l;
	}

}
