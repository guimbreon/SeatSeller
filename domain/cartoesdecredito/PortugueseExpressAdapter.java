package domain.cartoesdecredito;

import portugueseexpress.PortugueseExpress;

public class PortugueseExpressAdapter implements ISistemaDeCartoesDeCreditoAdapter{

	@Override
	public boolean validar(String num, int ccv, int mes, int ano) {
		PortugueseExpress api = new PortugueseExpress();
		api.setNumber("1234123412341234");
		api.setCcv(123);
		api.setMonth(5);
		api.setYear(2018);
		return api.validate();
		
	}

	@Override
	public boolean cativar(String num, int ccv, int mes, int ano, double qt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retirar(String num, int ccv, int mes, int ano, double qt) {
		// TODO Auto-generated method stub
		return false;
	}

}
