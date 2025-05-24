package domain.handlers;

import domain.api.IConcluirReservaHandler;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.InvalidCreditCardException;
import domain.cartoesdecredito.ISistemaDeCartoesDeCreditoAdapter;
import domain.core.reservas.CatalogoReservas;
import domain.core.utilizadores.Utilizador;

public class ConcluirReservaHandler implements IConcluirReservaHandler {

    private Utilizador utilizador;
    private CatalogoReservas catReservas;
    private ISistemaDeCartoesDeCreditoAdapter creditCardSystem;

    public ConcluirReservaHandler(Utilizador utilizador, CatalogoReservas catReservas,
            ISistemaDeCartoesDeCreditoAdapter creditCardSystem) {
        this.utilizador = utilizador;
        this.catReservas = catReservas;
        this.creditCardSystem = creditCardSystem;
    }

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
