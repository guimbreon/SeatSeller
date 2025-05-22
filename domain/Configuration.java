package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Configuration gathers all possible configuration variables defined in the config.properties files.
 * 
 * Configuration follows the singleton pattern.
 */
public class Configuration {
	private static Configuration INSTANCE = new Configuration();
	
	private static Logger LOGGER = Logger.getLogger(Configuration.class.getName());
	
	public static Configuration getInstance() {
		return INSTANCE;
	}
	
	private Properties prop = new Properties();
	
	private Configuration() {
		try(InputStream input = new FileInputStream("config.properties")) {
			prop.load(input);
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.CONFIG, "Failed to config", e);
		} catch (IOException e) {
			LOGGER.log(Level.CONFIG, "Failed to config", e);
		}
	}
	
	
	/**
    * Retorna true se a propriedade "cativar" estiver definida como "true".
    */
	public boolean cativarDuranteReservas() {
		String valor = prop.getProperty("cativar");
        return valor != null && valor.equalsIgnoreCase("true");
	}
	
	/**
    * Retorna o valor da propriedade "retirar" como double.
    * Caso não esteja definido, retorna 1.0 por omissão.
    */
	public double valorDuranteReservas() {
		String valor = prop.getProperty("retirar");
        if (valor == null) return 1.0;
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.CONFIG, "Invalid 'retirar' value in config.properties", e);
            return 1.0;
        }
	}
	
	
	/**
	* Devolve o valor de qualquer propriedade pelo nome.
	* @param key nome da propriedade
	* @return valor da propriedade, ou null se não estiver definida
	*/
	public String valorPropriedade(String key) {
		return prop.getProperty(key);
 	}
	
}
