package domain.handlers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import domain.Configuration;
import domain.api.IReservarLugarHandler;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.InvalidCreditCardException;
import domain.api.wrappers.Combinacao;
import domain.cartoesdecredito.ISistemaDeCartoesDeCreditoAdapter;
import domain.core.lugares.CatalogoGrelhas;
import domain.core.lugares.CatalogoTiposDeLugar;
import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;
import domain.core.pagamentos.CartaoCredito;
import domain.core.pagamentos.Pagamento;
import domain.core.reservas.CatalogoReservas;
import domain.core.reservas.LinhaReserva;
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
    private Utilizador cli;
    private double preco;
    private CartaoCredito cc;
    private LinhaReserva lr;
    
    
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
		cli = (Utilizador) this.utilizador;
		res.setCliente(cli);
		this.lr = res.novaLinha(date, time);
		return catGrelhas.getCombinacoes(date, time);
	}

	@Override
	public String indicarCombinacao(String grelha, String tipoDeLugar) throws DoesNotExistException {
		Grelha g = catGrelhas.getGrelha(grelha);
		Optional<TipoDeLugar> t = catTipos.getTipo(tipoDeLugar);
		LocalDate data = res.getDataCorrente();
		LocalTime hora = res.getHoraCorrente();
		Optional<Lugar> lug = g.getDisponivel(t, data, hora);
		lr.addLugar(lug.get(), t.get());
		return lug.toString();
	}

	
	@Override
	public void terminarLugares() {
		res.finalizar();
	}

	
	@Override
	public double indicarCC(String num, int ccv, int mes, int ano) throws InvalidCreditCardException {
		boolean b = this.creditCardSystem.validar(num, ccv, mes, ano);
		Utilizador cli = res.getCliente();
		if(b) {
			boolean temCC = cli.temCC(num);
			if(!temCC) {
				cc = cli.criaCC(num, ccv, mes, ano);
			}else {
				cc = new CartaoCredito(num, ccv, mes, ano);
			}
		}
		this.preco = res.getPreco();
		return preco;
	}

	@Override
	public String confirmarReserva() {
		Configuration conf = Configuration.getInstance();
		boolean cativar = conf.cativarDuranteReservas();
		double valor = conf.valorDuranteReservas();
		Pagamento pg = new Pagamento(cativar, valor);
		cli.registarPagamento(pg);
		res.registarPagamento(pg);
		cli.associarReserva(res);
		catReservas.registrarReserva(res);
		
		String num = cc.getNumero();
		int ccv = cc.getCcv();
		int mes = cc.getMesValidade();
		int ano = cc.getAnoValidade();
		
		if(cativar) {
			creditCardSystem.cativar(num, ccv, mes, ano, ano);
		}else {
			creditCardSystem.retirar(num, ccv, mes, ano, ano);
		}
		
		res.notificarGrelhas();
		return res.getCodigo();
	}

}
