package domain.cartoesdecredito;

import monstercard.Card;
import monstercard.MonsterCardAPI;

public class MonsterCardAdapter implements ISistemaDeCartoesDeCreditoAdapter {

	private MonsterCardAPI api = new MonsterCardAPI();

	@Override
	public boolean validar(String num, int ccv, int mes, int ano) {
		String mesStr = String.format("%02d", mes);
		String anoStr = String.format("%02d", ano); // 4digitos
		Card card = new Card(num, String.valueOf(ccv), mesStr, anoStr);
		
		return api.isValid(card);
	}

	@Override
	public boolean cativar(String num, int ccv, int mes, int ano, double qt) {
		String mesStr = String.format("%02d", mes);
		String anoStr = String.format("%02d", ano); // 4digitos
		Card card = new Card(num, String.valueOf(ccv), mesStr, anoStr);
		
		api.block(card, qt);
		return true;
	}

	@Override
	public boolean retirar(String num, int ccv, int mes, int ano, double qt) {
		String mesStr = String.format("%02d", mes);
		String anoStr = String.format("%02d", ano); // 4digitos
		Card card = new Card(num, String.valueOf(ccv), mesStr, anoStr);
		
		api.charge(card, qt);
		return true;
	}
}
