package domain.core.pagamentos;

public class Pagamento {

    private Boolean cativar;
    private double valor;

    // Construtor
    public Pagamento(Boolean cativar, double valor) {
        this.cativar = cativar;
        this.valor = valor;
    }

    
    public Boolean getCativar() {
        return cativar;
    }

    public double getValor() {
        return valor;
    }

}
