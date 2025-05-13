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
	
	public boolean cativarDuranteReservas() {
		// TO DO
	}
	
	public double valorDuranteReservas() {
		// TO DO
	}

	public String valorPropriedade(String key) {
		// TO DO
 	}
	
}
