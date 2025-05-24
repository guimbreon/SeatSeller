package domain.handlers;

import java.time.LocalDate;
import java.time.LocalTime;

import domain.api.IReservarLugarHandler;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.InvalidCreditCardException;
import domain.api.wrappers.Combinacao;

public class ReservarLugarHandler implements IReservarLugarHandler {

	@Override
	public void indicarCliente(String nCli) throws DoesNotExistException {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Combinacao> indicarDataHora(LocalDate date, LocalTime time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String indicarCombinacao(String grelha, String tipoDeLugar) throws DoesNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminarLugares() {
		// TODO Auto-generated method stub

	}

	@Override
	public double indicarCC(String num, int ccv, int mes, int ano) throws InvalidCreditCardException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String confirmarReserva() {
		// TODO Auto-generated method stub
		return null;
	}

}
