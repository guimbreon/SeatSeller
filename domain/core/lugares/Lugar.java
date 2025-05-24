package domain.core.lugares;

import java.util.Optional;

public class Lugar {

    private int linha;
    private int coluna;
    private TipoDeLugar tipo;
    private Grelha grelha;

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
        return String.format("Lugar [%d,%d] - Tipo: %s, Preço: %.2f€", linha, coluna, getPreco(), getDesignacaoTipo());
    }

    // Getters
    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    
    public double getPreco() {
        
    }

    public String getDesignacaoTipo() {

    }
    
    public void definirTipo(Optional<TipoDeLugar> tp) {
    	
    }

    public boolean disponivel(String data, String hora) {
    	
    }
}
