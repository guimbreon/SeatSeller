package domain.core.pagamentos;

public class Pagamento {

    private boolean cativar;
    private double valor;

    // Construtor
    public Pagamento(boolean cativar, double valor) {
        this.cativar = cativar;
        this.valor = valor;
    }

    
    public boolean getCativar() {
        return cativar;
    }

    public double getValor() {
        return valor;
    }

}
