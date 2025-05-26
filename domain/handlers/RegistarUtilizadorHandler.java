package domain.handlers;

import java.time.LocalTime;

import domain.api.IRegistarUtilizadorHandler;
import domain.core.utilizadores.CatalogoUtilizadores;

public class RegistarUtilizadorHandler  implements IRegistarUtilizadorHandler {
	
		public CatalogoUtilizadores catUtilizadores;
		
		public RegistarUtilizadorHandler(CatalogoUtilizadores catUtilizadores) {
			this.catUtilizadores = catUtilizadores;
		}
		
		
		 /**
	     * Regista um cliente final com nome de utilizador e password.
	     * 
	     * @param u Nome de utilizador.
	     * @param p Password.
	     */
		@Override
		public void registarClienteFinal(String u, String p) {
			catUtilizadores.registarClienteFinal(u, p);
		}

		  /**
	     * Regista um funcionário com nome de utilizador, password e horário de trabalho.
	     * 
	     * @param u Nome de utilizador.
	     * @param p Password.
	     * @param st Hora de início do turno.
	     * @param end Hora de fim do turno.
	     */
		@Override
		public void registarFuncionario(String u, String p, LocalTime st, LocalTime end) {
			catUtilizadores.registarFuncionario(u, p, st, end);
		}

		
		 /**
	     * Regista um administrador com nome de utilizador e password.
	     * 
	     * @param u Nome de utilizador.
	     * @param p Password.
	     */
		@Override
		public void registarAdministrador(String u, String p) {
			catUtilizadores.registarAdmin(u, p);
		}
	}