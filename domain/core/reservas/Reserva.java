package domain.core.reservas;


import domain.core.pagamentos.Pagamento;
import domain.core.utilizadores.ClienteFinal;

import java.util.List;
import java.util.ArrayList;

public class Reserva {
    private int codigo;
    private List<LinhaReserva> linhasReserva;
    private ClienteFinal client;
    private List<Pagamento> pagamentos;
    private LinhaReserva linhaCorrente;

    public Reserva(){
        this.linhasReserva = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
    }
    
    public int getCodigo(){
        return codigo;
    }

    public ClienteFinal getCliente () {
        return client;
    }

    public List<LinhaReserva> getLinhas() {
        return linhasReserva;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
    
    public String getDataCorrente() {
    	if (linhaCorrente == null) {
    		return null;}
    	return linhaCorrente.getData(); // ainda por definir na linhareserva
    }
    
    public String getHoraCorrente() {
    	if (linhaCorrente == null) {
    		return null;}
    	return linhaCorrente.getHora(); // ainda por definir na linhareserva
    }
    
    
    public void setCliente(ClienteFinal cli) {
        this.client = cli;
    }

    public LinhaReserva novaLinha(LocalDate data, LocalTime hora) {
        finalizar();
        LinhaReserva linhaCorrente = new LinhaReserva(data, hora);
        return linhaCorrente;
    }

    public void finalizar() {
        if(linhaCorrente != null){
            linhasReserva.add(linhaCorrente);
            linhaCorrente = null;
        }
    }
    
    public double getValorEmFalta() {
    	double total = 0;
    	for (LinhaReserva linha : linhasReserva) {
    		total += linha.getSubtotal(); //ainda por definir na LinhaReserva
    	}
    	double totalPago = 0;
    	for (Pagamento pagamento : pagamentos) {
    		totalPago += pagamento.getValor(); 
    	}
    	return total-totalPago;
    }
    
    public void notificarGrelhas() {
    	for (LinhaReserva linha : linhasReserva) {
    		linha.notificarGrelhas(); // ainda por definir tb na linhareserva
    	}
    }

    
    

    public void registarPagamento(Pagamento pg){
        pagamentos.add(pg);
    }

}
