package domain.core.utilizadores;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CatalogoUtilizadores {
	private Map<String, Utilizador> utilizadores = new HashMap<>();
	
	/**
     * Regista um administrador no catálogo.
     * 
     * @param u Username do administrador.
     * @param p Password do administrador.
     */
	public void registarAdmin(String u, String p) {
		Utilizador utilizador = new Administrador(u, p);
		utilizadores.put(u, utilizador);
	}
	/**
     * Regista um funcionário no catálogo.
     * 
     * @param u Username do funcionário.
     * @param p Password do funcionário.
     * @param st Hora de início do turno.
     * @param end Hora de fim do turno.
     */
	public void registarFuncionario(String u, String p, LocalTime st, LocalTime end) {
		Utilizador utilizador = new Funcionario(u, p, st, end);
		utilizadores.put(u, utilizador);
	}
	
	
	/**
     * Regista um cliente final no catálogo.
     * 
     * @param u Username do cliente.
     * @param p Password do cliente.
     */
	public void registarClienteFinal(String u, String p) {
		Utilizador utilizador = new ClienteFinal(u, p);
		utilizadores.put(u, utilizador);
	}

	
	 /**
     * Autentica um utilizador verificando se o username existe e a password está correta.
     * 
     * @param u Username do utilizador.
     * @param p Password para autenticação.
     * @return true se a autenticação for bem sucedida, false caso contrário.
     */
	public boolean autenticar(String u, String p) {
		return utilizadores.containsKey(u) && utilizadores.get(u).tryLogin(p);
	}

	
	  /**
     * Obtém um utilizador pelo seu username.
     * 
     * @param u Username do utilizador.
     * @return O utilizador correspondente, ou null se não existir.
     */
	public Utilizador getUtilizador(String u) {
		return utilizadores.get(u);
	}

	
	 /**
     * Obtém um cliente final pelo username, retornando um Optional.
     * 
     * @param username Username do cliente.
     * @return Optional contendo o cliente final se existir e for do tipo ClienteFinal,
     *         ou Optional vazio caso contrário.
     */
	public Optional<Utilizador> getCliente(String username) {
		return Optional.ofNullable(utilizadores.get(username)).filter(u -> u instanceof ClienteFinal);
	}
	
}
