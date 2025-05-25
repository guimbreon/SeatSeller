package domain.core.pagamentos;

import java.util.ArrayList;
import java.util.List;

public class CartaoCredito {

    private String numero;
    private int ccv;
    private int mesValidade;
    private int anoValidade;
    private List<Pagamento> pagamentos;

    // Construtor
    public CartaoCredito(String numero, int ccv, int mes, int ano) {
        this.numero = numero;
        this.ccv = ccv;
        this.mesValidade = mes;
        this.anoValidade = ano;
        
        this.pagamentos = new ArrayList<Pagamento>();
    }

    public void associarPagamento(Pagamento p) {
        pagamentos.add(p);
    }

	public String getNumero() {
		return this.numero;
	}
	
    public int getCcv() {
        return this.ccv;
    }

    public int getMesValidade() {
        return this.mesValidade;
    }

    public int getAnoValidade() {
        return this.anoValidade;
    }
    
}