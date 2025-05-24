package domain.handlers;

import java.time.LocalDate;
import java.time.LocalTime;

import domain.api.IReservarLugarHandler;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.InvalidCreditCardException;
import domain.api.wrappers.Combinacao;
import domain.cartoesdecredito.ISistemaDeCartoesDeCreditoAdapter;
import domain.core.lugares.CatalogoGrelhas;
import domain.core.lugares.CatalogoTiposDeLugar;
import domain.core.reservas.CatalogoReservas;
import domain.core.utilizadores.CatalogoUtilizadores;
import domain.core.utilizadores.Utilizador;

public class ReservarLugarHandler implements IReservarLugarHandler {

    private Utilizador utilizador;
    private CatalogoGrelhas catGrelhas;
    private CatalogoTiposDeLugar catTipos;
    private CatalogoReservas catReservas;
    private CatalogoUtilizadores catUtilizadores;
    private ISistemaDeCartoesDeCreditoAdapter creditCardSystem;

    public ReservarLugarHandler(Utilizador utilizador, CatalogoGrelhas catGrelhas, CatalogoTiposDeLugar catTipos,
            CatalogoReservas catReservas, CatalogoUtilizadores catUtilizadores,
            ISistemaDeCartoesDeCreditoAdapter creditCardSystem) {
        this.utilizador = utilizador;
        this.catGrelhas = catGrelhas;
        this.catTipos = catTipos;
        this.catReservas = catReservas;
        this.catUtilizadores = catUtilizadores;
        this.creditCardSystem = creditCardSystem;
    }

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
