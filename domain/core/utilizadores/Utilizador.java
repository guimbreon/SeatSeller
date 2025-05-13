package domain.core.utilizadores;

import java.util.ArrayList;
import java.util.List;

import domain.core.pagamentos.CartaoCredito;
import domain.core.pagamentos.Pagamento;
import domain.core.reservas.Reserva;

public abstract class Utilizador {
	private String username;
	private String password;
	
	private List<CartaoCredito> cartoes = new ArrayList<>();
	private List<Pagamento> pagamentos = new ArrayList<>();
	private List<Reserva> reservas = new ArrayList<>();
	
	public Utilizador(String u, String p) {
		this.username = u;
		this.password = p;
	}
	
	public boolean tryLogin(String p) {
		return p.equals(password);
	}

	public boolean temCC(String numero) {
		return cartoes.stream().filter(c -> c.getNumero().equals(numero)).count() > 0;
	}
	
	@Override
	public String toString() {
		return username;
	}

	public CartaoCredito criaCC(String numero, int ccv, int month, int year) {
		CartaoCredito cc = new CartaoCredito(numero, ccv, month, year);
		cartoes.add(cc);
		return cc;
	}

	public void registarPagamento(Pagamento p) {
		pagamentos.add(p);
		cartoes.get(cartoes.size()-1).associarPagamento(p);
		
	}

	public void associarReserva(Reserva res) {
		reservas.add(res);
	}
}
