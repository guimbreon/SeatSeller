package domain.core.lugares;

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

    public Lugar(int linha, int coluna, TipoDeLugar tipo, Grelha grelha) {
        this.linha = linha;
        this.coluna = coluna;
        this.tipo = tipo;
        this.grelha = grelha;
    }

    public void notificarGrelha() {
        grelha.notificar(this);
    }

    @Override
    public String toString() {
        return String.format("[%d,%d] - Tipo: %s, Preço: %.2f€", linha, coluna, getPreco(), getDesignacaoTipo());
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    
    public double getPreco() {
    	if (grelha == null || tipo == null) {
    	    return 0.0;
    	}
    	return grelha.getIndice() * tipo.getPreco();
    }

    public String getDesignacaoTipo() {
    	return (tipo != null) ? tipo.getDesig() : "Sem tipo";
    }
    
    public void definirTipo(Optional<TipoDeLugar> tp) {
        tp.ifPresent(t -> this.tipo = t);
    }
    
    public void addLinhaReserva(LinhaReserva lr) {
		ReservaLugar rl = new ReservaLugar(this, lr);
		this.reservaLugar.add(rl);
	}

    public boolean disponivel(String data, String hora) {
    	LinhaReserva linhaReserva;
    	for(ReservaLugar rl: this.reservaLugar) {
    		linhaReserva = rl.getLinhaReserva();
    		if(linhaReserva.getDate() == data || linhaReserva.getTime() == hora) {
    			return false;
    		}
    	}
    	return true;
    }
}
