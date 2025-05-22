package domain;

import java.util.Optional;

import domain.api.IRegistarUtilizadorHandler;
import domain.api.ISeatSeller;
import domain.api.ISessao;
import domain.cartoesdecredito.ISistemaDeCartoesDeCreditoAdapter;
import domain.cartoesdecredito.SistemaDeCartoesDeCreditoAdapterFactory;
import domain.core.lugares.CatalogoGrelhas;
import domain.core.lugares.CatalogoTiposDeLugar;
import domain.core.reservas.CatalogoReservas;
import domain.core.utilizadores.CatalogoUtilizadores;
import domain.handlers.RegistarUtilizadorHandler;
import domain.handlers.Sessao;

public class SeatSeller implements ISeatSeller {


	private CatalogoReservas catReservas = new CatalogoReservas();
	private CatalogoUtilizadores catUtilizadores = new CatalogoUtilizadores();
    private CatalogoTiposDeLugar catTipos = new CatalogoTiposDeLugar();
    private CatalogoGrelhas catGrelhas = new CatalogoGrelhas();
    
    private ISistemaDeCartoesDeCreditoAdapter creditCardSystem =
            SistemaDeCartoesDeCreditoAdapterFactory.getInstance().getSistemaDeCartoesDeCreditoAdapter();
	
	@Override
	public IRegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler(catUtilizadores);
	}

	@Override
	public Optional<ISessao> autenticar(String u, String p) {
		boolean autenticado = catUtilizadores.autenticar(u, p);
		if (!autenticado) return Optional.empty();
		
		Utilizador utilizador = catUtilizadores.getUtilizador(u);
        if (utilizador == null) return Optional.empty();
		
		return Optional.of(new Sessao(utilizador, catReservas, catUtilizadores, catTipos, catGrelhas, creditCardSystem));
	}

}
