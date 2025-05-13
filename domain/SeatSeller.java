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
    // Outros atributos
	
	@Override
	public IRegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler(catUtilizadores);
	}

	@Override
	public Optional<ISessao> autenticar(String u, String p) {
		boolean autenticado = catUtilizadores.autenticar(u, p);
		
		if (!autenticado) return Optional.empty();
		return Optional.of(new Sessao(/* parametros */));
	}

}
