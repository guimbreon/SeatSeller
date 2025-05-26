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

    
    /**
     * Associa um pagamento a este cartão de crédito.
     * 
     * @param p O pagamento a ser associado.
     */
    public void associarPagamento(Pagamento p) {
        pagamentos.add(p);
    }

    
    /**
     * Retorna o número do cartão de crédito.
     * 
     * @return Número do cartão.
     */
	public String getNumero() {
		return this.numero;
	}
	
	
	 /**
     * Retorna o código de segurança (CCV) do cartão.
     * 
     * @return Código CCV.
     */
    public int getCcv() {
        return this.ccv;
    }

    /**
     * Retorna o mês de validade do cartão.
     * 
     * @return Mês de validade.
     */
    public int getMesValidade() {
        return this.mesValidade;
    }

    /**
     * Retorna o ano de validade do cartão.
     * 
     * @return Ano de validade.
     */
    public int getAnoValidade() {
        return this.anoValidade;
    }
    
}