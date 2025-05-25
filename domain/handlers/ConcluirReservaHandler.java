package domain.handlers;

import domain.api.IConcluirReservaHandler;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.InvalidCreditCardException;
import domain.cartoesdecredito.ISistemaDeCartoesDeCreditoAdapter;
import domain.core.reservas.CatalogoReservas;
import domain.core.reservas.Reserva;
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
		Reserva res = this.catReservas.getReserva(codRes);
		return res.getValorEmFalta();
	}

	@Override
	public void indicarCC(String num, int ccv, int mes, int ano) throws InvalidCreditCardException {
		boolean b = this.creditCardSystem.validar(num, ccv, mes, ano);
		if(b) {
			//to do
		}
		
	}

}
