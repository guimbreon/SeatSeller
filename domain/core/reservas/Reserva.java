package domain.core.reservas;


import domain.core.pagamentos.Pagamento;
import domain.core.utilizadores.ClienteFinal;
import domain.core.utilizadores.Utilizador;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Reserva {
    private String codigo;
    private List<LinhaReserva> linhasReserva;
    private Utilizador client;
    private List<Pagamento> pagamentos;
    private LinhaReserva linhaCorrente ;

    public Reserva(String codigo){
    	this.codigo = codigo;
        this.linhasReserva = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
    }
    
    /**
     * Obtém o código da reserva.
     * 
     * @return código identificador da reserva
     */
    public String getCodigo(){
    	
        return codigo;
    }

    /**
     * Obtém o cliente que fez a reserva.
     * 
     * @return o utilizador associado à reserva
     */
    public Utilizador getCliente () {
        return client;
    }

    /**
     * Obtém todas as linhas de reserva associadas a esta reserva.
     * 
     * @return lista de linhas de reserva
     */
    public List<LinhaReserva> getLinhas() {
        return linhasReserva;
    }

    /**
     * Obtém todos os pagamentos realizados para esta reserva.
     * 
     * @return lista de pagamentos
     */
    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
    
    /**
     * Obtém a data da linha de reserva corrente.
     * 
     * @return data da linha corrente ou null se não existir
     */
    public LocalDate getDataCorrente() {
    	if (linhaCorrente == null) {
    		return null;}
    	return linhaCorrente.getDate(); // ainda por definir na linhareserva
    }
    
    /**
     * Obtém a hora da linha de reserva corrente.
     * 
     * @return hora da linha corrente ou null se não existir
     */
    public LocalTime getHoraCorrente() {
    	if (linhaCorrente == null) {
    		return null;}
    	return linhaCorrente.getTime(); // ainda por definir na linhareserva
    }
    
    /**
     * Obtém o preço subtotal da linha de reserva corrente.
     * 
     * @return subtotal da linha corrente ou 0 se não existir
     */
    public double getPreco() {
    	return linhaCorrente.getSubtotal();
    }
    
    /**
     * Define o cliente associado a esta reserva.
     * 
     * @param cli utilizador que realizou a reserva
     */
    public void setCliente(Utilizador cli) {
        this.client = cli;
    }
    

    /**
     * Cria uma nova linha de reserva para a data e hora especificadas, 
     * finalizando a linha corrente se existir.
     * 
     * @param date data da nova linha de reserva
     * @param time hora da nova linha de reserva
     * @return a nova linha de reserva criada
     */
    public LinhaReserva novaLinha(LocalDate date, LocalTime time) {
        finalizar();
        this.linhaCorrente = new LinhaReserva(date, time);
        return this.linhaCorrente;
    }

    /**
     * Finaliza a linha de reserva corrente, adicionando-a à lista de linhas.
     */
    public void finalizar() {
        if(linhaCorrente != null){
            linhasReserva.add(linhaCorrente);
        }
    }
    
    /**
     * Calcula o valor em falta para pagamento da reserva.
     * 
     * @return valor total da reserva ainda não pago
     */
    public double getValorEmFalta() {
    	double total = 0;
    	for (LinhaReserva linha : linhasReserva) {
    		total += linha.getSubtotal(); //ainda por definir na LinhaReserva
    	}
    	double totalPago = 0;
    	if(!pagamentos.isEmpty()) {
    		for (Pagamento pagamento : pagamentos) {
        		totalPago += pagamento.getValor(); 
        	}	
    	}
    	
    	return total-totalPago;
    }
    
    /**
     * Notifica todas as grelhas envolvidas nas linhas de reserva.
     */
    public void notificarGrelhas() {
    	for (LinhaReserva linha : linhasReserva) {
    		linha.notificarGrelhas(); // ainda por definir tb na linhareserva
    	}
    }

    /**
     * Regista um pagamento para esta reserva.
     * 
     * @param pg pagamento a ser adicionado
     */
    public void registarPagamento(Pagamento pg){
        pagamentos.add(pg);
    }

}
