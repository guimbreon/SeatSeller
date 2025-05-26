package domain.core.reservas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;

public class LinhaReserva {


	private LocalDate data;
	private LocalTime hora;
    private List<ReservaLugar> reservaLugar;
    private List<TipoDeLugar> lugares;
    private Grelha grelha;
    
	public LinhaReserva(LocalDate data, LocalTime hora) {
		this.data = data;
		this.hora = hora;
		this.reservaLugar = new ArrayList<>();;
		this.lugares = new ArrayList<>();
		
	}
	 /**
     * Calcula o subtotal desta linha de reserva, somando o preço de todos os tipos
     * de lugar reservados.
     * 
     * @return subtotal (preço total) da linha de reserva
     */
	public double getSubtotal() {
		int subtotal = 0;
		
    	for(TipoDeLugar rl: this.lugares) {
    		subtotal += rl.getPreco() * grelha.getIndice();
    	}
		return subtotal;
	}

	  /**
     * Notifica todas as grelhas associadas aos lugares reservados nesta linha.
     */
	public void notificarGrelhas() {
    	for(ReservaLugar rl: this.reservaLugar) {
    		rl.getLugar().notificarGrelha();
    	}
	}

	  /**
     * Obtém a data desta linha de reserva.
     * 
     * @return data da reserva
     */
	public LocalDate getDate() {
		return this.data;
	}
	
	
	   /**
     * Obtém a hora desta linha de reserva.
     * 
     * @return hora da reserva
     */
	public LocalTime getTime() {
		return this.hora;
	}
	
	 /**
     * Adiciona um lugar e o seu tipo associado a esta linha de reserva.
     * 
     * @param lug lugar reservado
     * @param lr  tipo do lugar reservado
     */
	public void addLugar(Lugar lug, TipoDeLugar lr, Grelha g) {
		ReservaLugar rl = new ReservaLugar(lug, this);
		lugares.add(lr);
		grelha = g;
		this.reservaLugar.add(rl);
	}

}
