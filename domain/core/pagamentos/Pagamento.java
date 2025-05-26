package domain.core.pagamentos;

public class Pagamento {

    private boolean cativar;
    private double valor;

    // Construtor
    public Pagamento(boolean cativar, double valor) {
        this.cativar = cativar;
        this.valor = valor;
    }

    /**
     * Verifica se o pagamento é para cativar (bloquear) o valor.
     * 
     * @return true se o pagamento é para cativar; false caso contrário.
     */
    public boolean getCativar() {
        return cativar;
    }
    /**
     * Retorna o valor do pagamento.
     * 
     * @return o valor monetário do pagamento.
     */
    public double getValor() {
        return valor;
    }

}
