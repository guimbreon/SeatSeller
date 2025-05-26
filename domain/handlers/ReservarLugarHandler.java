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

    
    /**
     * Indica o cliente responsável pela reserva.
     * 
     * @param nCli Nome ou identificador do cliente.
     * @throws DoesNotExistException Se o cliente não existir no catálogo.
     */
	@Override
	public void indicarCliente(String nCli) throws DoesNotExistException {
		this.catUtilizadores.getCliente(nCli);
	}

	
	 /**
     * Indica a data e hora da reserva e inicializa a linha corrente da reserva.
     * 
     * @param date Data da reserva.
     * @param time Hora da reserva.
     * @return Iterable com as combinações de grelha e tipo de lugar disponíveis nessa data e hora.
     */
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

	
	/**
     * Indica a combinação escolhida de grelha e tipo de lugar para reserva.
     * 
     * @param grelha Nome da grelha.
     * @param tipoDeLugar Designação do tipo de lugar.
     * @return Representação textual do lugar reservado.
     * @throws DoesNotExistException Se a grelha ou tipo de lugar não existir.
     */
	@Override
	public String indicarCombinacao(String grelha, String tipoDeLugar) throws DoesNotExistException {
		Grelha g = catGrelhas.getGrelha(grelha);
		Optional<TipoDeLugar> t = catTipos.getTipo(tipoDeLugar);
		LocalDate data = res.getDataCorrente();
		LocalTime hora = res.getHoraCorrente();
		Optional<Lugar> lug = g.getDisponivel(t, data, hora);
		lr.addLugar(lug.get(), t.get(), g);
		return lug.toString();
	}

    /**
     * Finaliza a adição de lugares na linha corrente da reserva.
     */
	@Override
	public void terminarLugares() {
		res.finalizar();
	}

    /**
     * Indica os dados do cartão de crédito para validação e obtenção do preço da reserva.
     * 
     * @param num Número do cartão.
     * @param ccv Código de verificação.
     * @param mes Mês de validade.
     * @param ano Ano de validade.
     * @return Preço total da reserva.
     * @throws InvalidCreditCardException Se os dados do cartão não forem válidos.
     */
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

	
	 /**
     * Confirma a reserva, registra o pagamento e notifica as grelhas.
     * 
     * @return Código da reserva confirmada.
     */
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
