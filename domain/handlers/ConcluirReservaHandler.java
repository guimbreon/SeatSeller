package domain.handlers;

import domain.api.IConcluirReservaHandler;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.InvalidCreditCardException;

public class ConcluirReservaHandler implements IConcluirReservaHandler {

	@Override
	public double confirmarValorEmFalta(String codRes) throws DoesNotExistException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void indicarCC(String num, int ccv, int mes, int ano) throws InvalidCreditCardException {
		// TODO Auto-generated method stub

	}

}
