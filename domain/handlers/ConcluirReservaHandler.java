package domain.handlers;

import domain.api.IConcluirReservaHandler;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.InvalidCreditCardException;
import domain.cartoesdecredito.ISistemaDeCartoesDeCreditoAdapter;
import domain.core.pagamentos.Pagamento;
import domain.core.reservas.CatalogoReservas;
import domain.core.reservas.Reserva;
import domain.core.utilizadores.ClienteFinal;
import domain.core.utilizadores.Utilizador;

public class ConcluirReservaHandler implements IConcluirReservaHandler {

    private Utilizador utilizador;
    private CatalogoReservas catReservas;
    private ISistemaDeCartoesDeCreditoAdapter creditCardSystem;
    private Reserva res;
    private double valorFalta; 
    
    public ConcluirReservaHandler(Utilizador utilizador, CatalogoReservas catReservas,
            ISistemaDeCartoesDeCreditoAdapter creditCardSystem) {
        this.utilizador = utilizador;
        this.catReservas = catReservas;
        this.creditCardSystem = creditCardSystem;
    }

    /**
     * Obtém o valor que falta pagar para a reserva identificada pelo código.
     * 
     * @param codRes Código da reserva.
     * @return Valor em falta para pagamento.
     * @throws DoesNotExistException Se a reserva não for encontrada.
     */
	@Override
	public double confirmarValorEmFalta(String codRes) throws DoesNotExistException {
		res = catReservas.getReserva(codRes);
		if (res == null) {
			return 0;
		}
		valorFalta = res.getValorEmFalta();
		return valorFalta;
	}

	 /**
     * Indica os dados do cartão de crédito para realizar o pagamento do valor em falta.
     * Valida o cartão e registra o pagamento.
     * 
     * @param num Número do cartão de crédito.
     * @param ccv Código CCV do cartão.
     * @param mes Mês de validade do cartão.
     * @param ano Ano de validade do cartão.
     * @throws InvalidCreditCardException Se os dados do cartão forem inválidos.
     */
	@Override
	public void indicarCC(String num, int ccv, int mes, int ano) throws InvalidCreditCardException {
		boolean b = this.creditCardSystem.validar(num, ccv, mes, ano);
		if(res != null) {
			Utilizador cli = res.getCliente();
			if(b) {
				boolean temCC = cli.temCC(num);
				if(!temCC) {
					cli.criaCC(num, ccv, mes, ano);
				}
			}
			Pagamento pg = new Pagamento(false, valorFalta);
			cli.registarPagamento(pg);
			creditCardSystem.retirar(num, ccv, mes, ano, ano);
		}
	}

}
