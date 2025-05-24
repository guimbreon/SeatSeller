package domain.core.reservas;

import domain.core.pagamentos.Pagamento; 
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int codigo;
    private List<LinhaReserva> linhasReserva;
    private ClienteFinal cli;
    private List<Pagamento> pagamentos;

    public Reserva(int id, LocalDate data, LocalTime hora){
        this.codigo = codigo;
        this.linhasReserva = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
    }
    
    
    
    public int getCodigo(){
        return codigo;
    }

    public ClienteFinal getCliente () {
        return cliente;
    }

    public List<LinhaReserva> getLinhas() {
        return linhasReserva;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setCliente(Client cli) {
        this.cli = cliente;
    }

    public LinhaReserva novaLinha(LocalDate data, LocalTime hora) {
        finalizar();
        linhaCorrente = LinhaReserva.create(data, hora);
        return linhaCorrente;
    }

    public void finalizar() {
        if(linhaCorrente != null){
            linhas.add(linhaCorrente);
            linhaCorrente = null;
        }
    }

    public void notificarGrelhas() {
        linhas.forEach(LinhaReserva::notificarGrelhas);
    }

    public double getSubtotal() {
        return linhas.stream().mapToDouble(LinhaReserva::getSubtotal).sum();
    }

    public double getTotalPago() {
        return pagamentos.stream().mapToDouble(Pagamento::getValor).sum();
    }

    public double getValorEmfalta() {
        return getSubtotal - getTotalPago();
    }

    public void registarPagamento(Pagamento pg){
        pagamentos.add(pg);
    }

}
