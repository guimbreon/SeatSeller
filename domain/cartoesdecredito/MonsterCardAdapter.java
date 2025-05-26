package domain.cartoesdecredito;

import monstercard.Card;

import monstercard.MonsterCardAPI;

public class MonsterCardAdapter implements ISistemaDeCartoesDeCreditoAdapter {

	private MonsterCardAPI api = new MonsterCardAPI();

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
		String mesStr = String.format("%02d", mes);
		String anoStr = String.format("%02d", ano); // 4digitos
		Card card = new Card(num, String.valueOf(ccv), mesStr, anoStr);
		
		return api.isValid(card);
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
		String mesStr = String.format("%02d", mes);
		String anoStr = String.format("%02d", ano); // 4digitos
		Card card = new Card(num, String.valueOf(ccv), mesStr, anoStr);
		
		api.block(card, qt);
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
		String mesStr = String.format("%02d", mes);
		String anoStr = String.format("%02d", ano); // 4digitos
		Card card = new Card(num, String.valueOf(ccv), mesStr, anoStr);
		
		api.charge(card, qt);
		return true;
	}
}
