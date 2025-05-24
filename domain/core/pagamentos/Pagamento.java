package domain.core.pagamentos;

public class Pagamento {

    private String tipo;
    private double valor;

    // Construtor
    public Pagamento(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    
    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

}
