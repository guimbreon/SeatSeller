package domain.core.lugares;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.core.reservas.LinhaReserva;
import domain.core.reservas.ReservaLugar;

public class Lugar {

    private int linha;
    private int coluna;
    private TipoDeLugar tipo;
    private Grelha grelha;
    private List<ReservaLugar> reservaLugar;

    /**
     * Construtor para criar um lugar.
     *
     * @param linha a linha onde o lugar se encontra
     * @param coluna a coluna onde o lugar se encontra
     * @param tipo o tipo de lugar (ex: normal, VIP, etc.)
     * @param grelha a grelha à qual o lugar pertence
     */
    public Lugar(int linha, int coluna, TipoDeLugar tipo, Grelha grelha) {
        this.linha = linha;
        this.coluna = coluna;
        this.tipo = tipo;
        this.grelha = grelha;
        this.reservaLugar = new ArrayList<>();    }

    /**
     * Notifica a grelha associada de que este lugar sofreu uma alteração.
     */
    public void notificarGrelha() {
        grelha.notificar(this);
    }

    /**
     * Retorna uma representação textual do lugar.
     *
     * @return string com linha, coluna, tipo e preço
     */
    @Override
    public String toString() {
        return String.format("[%d,%d] - Tipo: %s, Preço: %.2f€", linha, coluna, getDesignacaoTipo(),  getPreco());
    }

    /**
     * Obtém a linha do lugar.
     *
     * @return o número da linha
     */
    public int getLinha() {
        return linha;
    }

    /**
     * Obtém a coluna do lugar.
     *
     * @return o número da coluna
     */
    public int getColuna() {
        return coluna;
    }
    
    /**
     * Calcula o preço do lugar com base no tipo e no índice da grelha.
     *
     * @return o preço do lugar
     */
    public double getPreco() {
    	if (grelha == null || tipo == null) {
    	    return 0.0;
    	}
    	return grelha.getIndice() * tipo.getPreco();
    }

    /**
     * Obtém a designação do tipo de lugar.
     *
     * @return a designação do tipo ou "Sem tipo" se for nulo
     */
    public String getDesignacaoTipo() {
    	return (tipo != null) ? tipo.getDesig() : "Sem tipo";
    }


    /**
     * Define ou redefine o tipo de lugar, se presente.
     *
     * @param tp o tipo de lugar encapsulado em Optional
     */
    public void definirTipo(Optional<TipoDeLugar> tp) {
        tp.ifPresent(t -> this.tipo = t);
    }
    
    /**
     * Associa uma linha de reserva a este lugar.
     *
     * @param lr a linha de reserva
     */
    public void addLinhaReserva(LinhaReserva lr) {
		ReservaLugar rl = new ReservaLugar(this, lr);
		this.reservaLugar.add(rl);
	}

    /**
     * Verifica se o lugar está disponível para uma data e hora específicas.
     *
     * @param date a data da sessão
     * @param time a hora da sessão
     * @return true se o lugar estiver disponível, false se já estiver reservado
     */
    public boolean disponivel(LocalDate date, LocalTime time) {
    	LinhaReserva linhaReserva;
    	for(ReservaLugar rl: this.reservaLugar) {
    		linhaReserva = rl.getLinhaReserva();
    		if(linhaReserva.getDate() == date || linhaReserva.getTime() == time) {
    			return false;
    		}
    	}
    	return true;
    }
}
