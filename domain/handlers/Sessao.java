package domain.handlers;

import domain.api.IAssociarGrelhaHandler;
import domain.api.IConcluirReservaHandler;
import domain.api.ICriarGrelhaHandler;
import domain.api.ICriarTipoDeLugarHandler;
import domain.api.IDesassociarGrelhaHandler;
import domain.api.IReservarLugarHandler;
import domain.api.ISessao;
import domain.cartoesdecredito.ISistemaDeCartoesDeCreditoAdapter;
import domain.cartoesdecredito.SistemaDeCartoesDeCreditoAdapterFactory;
import domain.core.lugares.CatalogoGrelhas;
import domain.core.lugares.CatalogoTiposDeLugar;
import domain.core.reservas.CatalogoReservas;
import domain.core.utilizadores.Administrador;
import domain.core.utilizadores.CatalogoUtilizadores;
import domain.core.utilizadores.ClienteFinal;
import domain.core.utilizadores.Funcionario;
import domain.core.utilizadores.Utilizador;

public class Sessao implements ISessao {

	private Utilizador utilizador;
	private CatalogoReservas catReservas;
	private CatalogoUtilizadores catUtilizadores;
	private CatalogoTiposDeLugar catTipos;
	private CatalogoGrelhas catGrelhas;
	
	private ISistemaDeCartoesDeCreditoAdapter creditCardSystem = 
			SistemaDeCartoesDeCreditoAdapterFactory.getInstance().getSistemaDeCartoesDeCreditoAdapter();

	public Sessao(Utilizador utilizador, CatalogoReservas catReservas, CatalogoUtilizadores catUtilizadores,
            CatalogoTiposDeLugar catTipos, CatalogoGrelhas catGrelhas) {
			  this.utilizador = utilizador;
			  this.catReservas = catReservas;
			  this.catUtilizadores = catUtilizadores;
			  this.catTipos = catTipos;
			  this.catGrelhas = catGrelhas;
			}

	@Override
	public boolean isClienteFinal() {
		return utilizador instanceof ClienteFinal;
	}

	@Override
	public boolean isAdministrador() {
		return utilizador instanceof Administrador;
	}

	@Override
	public boolean isFuncionario() {
		return utilizador instanceof Funcionario;
	}

	@Override
	public ICriarTipoDeLugarHandler getCriarTipoDeLugarHandler() {
	    return new CriarTipoDeLugarHandler(catTipos);
	}
	
	@Override
	public ICriarGrelhaHandler getCriarGrelhaHandler() {
	    return new CriarGrelhaHandler(catGrelhas, catTipos);
	}
	
	@Override
	public IReservarLugarHandler getReservarLugarHandler() {
	    return new ReservarLugarHandler(utilizador,catGrelhas,catTipos,catReservas,catUtilizadores,creditCardSystem);
	}
	
	@Override
	public IConcluirReservaHandler getConcluirReservaHandler() {
	    return new ConcluirReservaHandler(utilizador, catReservas, creditCardSystem);
	}
	
	@Override
	public IAssociarGrelhaHandler getAssociarGrelhaHandler() {
	    return new AssociarGrelhaHandler(utilizador, catGrelhas);
	}
	
	@Override
	public IDesassociarGrelhaHandler getDesassociarGrelhaHandler() {
	    return new DesassociarGrelhaHandler(utilizador, catGrelhas);
	}
	
	// Mais metodos

}
