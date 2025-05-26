package console;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.SeatSeller;
import domain.api.IConcluirReservaHandler;
import domain.api.ICriarGrelhaHandler;
import domain.api.INotificacaoReceiver;
import domain.api.IReservarLugarHandler;
import domain.api.ISeatSeller;
import domain.api.ISessao;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.InvalidCreditCardException;
import domain.api.exceptions.NonUniqueException;
import domain.api.wrappers.Combinacao;

public class Startup {
	private static Logger LOGGER = Logger.getLogger(Startup.class.getName());
	
	ISeatSeller app = new SeatSeller();
	 
	
	public Startup() {
		criarContas();
		
		app.autenticar("admin", "admin").ifPresent((serv) -> {
			criarTiposDeLugar(serv);
			criarGrelhas(serv);
				
			registarFuncionario();
				
			criarReservas(serv);
			concluirReserva(serv);
		});
	}


	private void registarFuncionario() {
		app.autenticar("serafim", "serafim").ifPresent(serv -> {
			if (serv.isFuncionario()) {
				LOGGER.info("Funcionario Serafim registado");
				INotificacaoReceiver not = (lugar) ->  LOGGER.info("Notificação ao Serafim: " + lugar);
				serv.getAssociarGrelhaHandler().associarGrelha("Sala VIP", not);
				LOGGER.info("Serafim associado a grelha Sala VIP");
				serv.getAssociarGrelhaHandler().associarGrelha("Sala 1", not);
				LOGGER.info("Serafim associado a grelha Sala 1");
			}
		});
	}


	private void criarContas() {
		
		app.getRegistarUtilizadorHandler().registarAdministrador("admin", "admin");
		app.getRegistarUtilizadorHandler().registarFuncionario("zacarias", "zacarias", LocalTime.parse("13:00"), LocalTime.parse("20:00"));
		app.getRegistarUtilizadorHandler().registarFuncionario("serafim", "serafim", LocalTime.parse("00:00"), LocalTime.parse("23:59"));
		
		app.getRegistarUtilizadorHandler().registarClienteFinal("ana", "ana");
		app.getRegistarUtilizadorHandler().registarClienteFinal("maria", "maria");
		LOGGER.info("Utilizadores criados");
	}

	private void criarTiposDeLugar(ISessao serv) {
		serv.getCriarTipoDeLugarHandler().criarTipoDeLugar("Lugar Normal", "Lugar típico na sala de cinema", 7.00, false);
		serv.getCriarTipoDeLugarHandler().criarTipoDeLugar("Lugar VIP", "Lugar com cadeira reclinavel", 10.00, false);
		serv.getCriarTipoDeLugarHandler().criarTipoDeLugar("Lugar Love Seat", "Lugar sem divisória de um dos lados", 8.00, false);
		serv.getCriarTipoDeLugarHandler().criarTipoDeLugar("Lugar Acessível", "Lugar adaptado a cadeiras de rodas", 2.00, false);

		LOGGER.info("Tipos de lugares criados");
	}
	

	private void criarGrelhas(ISessao serv)  {
		criarGrelha1(serv);
		criarGrelha2(serv);
		LOGGER.info("Grelhas criadas");
	}
	
	private void criarGrelha1(ISessao serv)  {
		ICriarGrelhaHandler cgh = serv.getCriarGrelhaHandler();
		try {
			cgh.iniciarGrelha("Sala 1", 1.0);
		} catch (NonUniqueException e) {
			LOGGER.log(Level.SEVERE, "Grelha já existe", e);
		}
		Optional<String> tipoDeLugarPadrao = cgh.indicarDimensao(10,10);
		
		if (!tipoDeLugarPadrao.isPresent()) {
			try {
				cgh.indicarTipoPadrao("Lugar Normal");
				for(int i=0; i<10; i++) {
					cgh.indicarTipoLugar(5, i, "Lugar Acessível");
				}
			} catch (DoesNotExistException e) {
				LOGGER.log(Level.SEVERE, "Tipo de Lugar não existe", e);
			}
		}
		cgh.terminar();
	}
	
	private void criarGrelha2(ISessao serv) {
		ICriarGrelhaHandler cgh = serv.getCriarGrelhaHandler();
		try {
			cgh.iniciarGrelha("Sala VIP", 1.2);
		} catch (NonUniqueException e) {
			LOGGER.log(Level.SEVERE, "Grelha já existe", e);
		}
		Optional<String> tipoDeLugarPadrao = cgh.indicarDimensao(5,10);
		
		if (!tipoDeLugarPadrao.isPresent()) {
			try {
				cgh.indicarTipoPadrao("Lugar VIP");
				for(int i=0; i<10; i++) {
					cgh.indicarTipoLugar(4, i, "Lugar Acessível");
				}
			} catch (DoesNotExistException e) {
				LOGGER.log(Level.SEVERE, "Tipo de Lugar não existe", e);
			}
		}
		cgh.terminar();
	}
	
	private void criarReservas(ISessao serv) {
		IReservarLugarHandler rlh = serv.getReservarLugarHandler();
		try {
			
			if (!serv.isClienteFinal()) {
				rlh.indicarCliente("ana");
			}
			LocalDate date = LocalDate.of(2025, 05, 02);
			LocalTime time = LocalTime.of(17, 00);
			Iterable<Combinacao> combs = rlh.indicarDataHora(date, time);
			
			Combinacao c0 = combs.iterator().next();
			String l = rlh.indicarCombinacao(c0.getGrelha(), c0.getTipoDeLugar());
			LOGGER.info("Atribuido lugar " + l);
			
			rlh.terminarLugares();
			
			double preco = -1.0;
			try {
				preco = rlh.indicarCC("1994132412342221", 123, 7, 2025);
			} catch (InvalidCreditCardException e) {
				LOGGER.log(Level.SEVERE, "Cartão de Crédito Inválido: ", e);
			}
			LOGGER.info("Preco: " + preco);
			String cod = rlh.confirmarReserva();
			LOGGER.info("Confirmada Reserva: " + cod);
			
		} catch (DoesNotExistException e) {
			LOGGER.log(Level.SEVERE, "Erro: ", e);
		}
	}
	

	private void concluirReserva(ISessao serv) {
		IConcluirReservaHandler crh = serv.getConcluirReservaHandler();
		try {
			double val = crh.confirmarValorEmFalta("R0");
			LOGGER.info("Valor em falta: " + val);
			try {
				crh.indicarCC("1994132412342223", 123, 7, 2025);
			} catch (InvalidCreditCardException e) {
				LOGGER.log(Level.SEVERE, "Cartão de Crédito Inválido: ", e);
			}
		} catch (DoesNotExistException e) {
			LOGGER.log(Level.SEVERE, "Erro: ", e);
		}
	}
}
