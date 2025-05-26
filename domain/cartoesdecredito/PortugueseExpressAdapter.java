package domain.cartoesdecredito;

import portugueseexpress.PortugueseExpress;

public class PortugueseExpressAdapter implements ISistemaDeCartoesDeCreditoAdapter{

	/**
	 * Valida um cartão de crédito utilizando a API MonsterCard.
	 *
	 * @param num o número do cartão
	 * @param ccv o código de segurança do cartão (CCV)
	 * @param mes o mês de validade (1-12)
	 * @param ano o ano de validade (com 4 dígitos)
	 * @return true se o cartão for válido, false caso contrário
	 */
	@Override
	public boolean validar(String num, int ccv, int mes, int ano) {
		PortugueseExpress api = new PortugueseExpress();
		
		api.setNumber(num);
		api.setCcv(ccv);
		api.setMonth(mes);
		api.setYear(ano);
		
		return api.validate();
		
	}

	/**
	 * Cativa (reserva) um determinado valor no cartão de crédito.
	 *
	 * @param num o número do cartão
	 * @param ccv o código de segurança do cartão (CCV)
	 * @param mes o mês de validade (1-12)
	 * @param ano o ano de validade (com 4 dígitos)
	 * @param qt o valor a ser cativado
	 * @return true se a operação for realizada
	 */
	@Override
	public boolean cativar(String num, int ccv, int mes, int ano, double qt) {
		PortugueseExpress api = new PortugueseExpress();
				
		api.setNumber(num);
		api.setCcv(ccv);
		api.setMonth(mes);
		api.setYear(ano);
		
		api.block(qt);
		
		return true;
	}

	/**
	 * Retira (efetiva a cobrança de) um determinado valor do cartão de crédito.
	 *
	 * @param num o número do cartão
	 * @param ccv o código de segurança do cartão (CCV)
	 * @param mes o mês de validade (1-12)
	 * @param ano o ano de validade (com 4 dígitos)
	 * @param qt o valor a ser retirado
	 * @return true se a operação for realizada
	 */
	@Override
	public boolean retirar(String num, int ccv, int mes, int ano, double qt) {
		PortugueseExpress api = new PortugueseExpress();
				
		api.setNumber(num);
		api.setCcv(ccv);
		api.setMonth(mes);
		api.setYear(ano);

		
		api.charge(qt);
		
		return true;
	}

}
