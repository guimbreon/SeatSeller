package domain.cartoesdecredito;

import portugueseexpress.PortugueseExpress;

public class PortugueseExpressAdapter implements ISistemaDeCartoesDeCreditoAdapter{

	@Override
	public boolean validar(String num, int ccv, int mes, int ano) {
		PortugueseExpress api = new PortugueseExpress();
		
		api.setNumber(num);
		api.setCcv(ccv);
		api.setMonth(mes);
		api.setYear(ano);
		
		return api.validate();
		
	}

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
