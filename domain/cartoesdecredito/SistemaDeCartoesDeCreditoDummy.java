package domain.cartoesdecredito;

public class SistemaDeCartoesDeCreditoDummy implements ISistemaDeCartoesDeCreditoAdapter{

	
	@Override
	public boolean validar(String num, int ccv, int mes, int ano) {
		
		return true;
	}

	@Override
	public boolean cativar(String num, int ccv, int mes, int ano, double qt) {
		
		return true;
	}

	@Override
	public boolean retirar(String num, int ccv, int mes, int ano, double qt) {
		
		return true;
	}

}