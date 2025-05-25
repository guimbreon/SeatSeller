package domain.handlers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import domain.api.IReservarLugarHandler;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.InvalidCreditCardException;
import domain.api.wrappers.Combinacao;
import domain.cartoesdecredito.ISistemaDeCartoesDeCreditoAdapter;
import domain.core.lugares.CatalogoGrelhas;
import domain.core.lugares.CatalogoTiposDeLugar;
import domain.core.lugares.Grelha;
import domain.core.lugares.TipoDeLugar;
import domain.core.reservas.CatalogoReservas;
import domain.core.reservas.Reserva;
import domain.core.reservas.ReservaFactory;
import domain.core.utilizadores.CatalogoUtilizadores;
import domain.core.utilizadores.ClienteFinal;
import domain.core.utilizadores.Utilizador;

public class ReservarLugarHandler implements IReservarLugarHandler {

    private Utilizador utilizador;
    private CatalogoGrelhas catGrelhas;
    private CatalogoTiposDeLugar catTipos;
    private CatalogoReservas catReservas;
    private CatalogoUtilizadores catUtilizadores;
    private ISistemaDeCartoesDeCreditoAdapter creditCardSystem;
    private Reserva res;

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
		this.catUtilizadores.getCliente(nCli);
	}

	@Override
	public Iterable<Combinacao> indicarDataHora(LocalDate date, LocalTime time) {
		if(this.res == null) {
			ReservaFactory rf = ReservaFactory.getInstance();
			rf.addCatReservas(catReservas);
			this.res = rf.getProximaReserva();			
		}
		ClienteFinal cf = (ClienteFinal) this.utilizador;
		res.setCliente(cf);
		res.novaLinha(date, time);
		catGrelhas.getCombinacoes(date, time);
		return null;
	}

	@Override
	public String indicarCombinacao(String grelha, String tipoDeLugar) throws DoesNotExistException {
		Grelha g = catGrelhas.getGrelha(grelha);
		Optional<TipoDeLugar> t = catTipos.getTipo(tipoDeLugar);
		LocalDate data = res.getDataCorrente();
		LocalTime time = res.getHoraCorrente();
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
