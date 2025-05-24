package domain.core.lugares.alocacao;

import domain.Configuration;



public class EncontrarLugarStrategyFactory {
	private static EncontrarLugarStrategyFactory INSTANCE = new EncontrarLugarStrategyFactory();
	
	private EncontrarLugarStrategyFactory() {
        // Construtor privado para o padrão Singleton
	}
	

    public static EncontrarLugarStrategyFactory getInstance() {
        return INSTANCE;
    }
	
    
    public IEncontrarLugarStrategy getEncontrarLugarStrategy() {
        try {
            String className = Configuration.getInstance().valorPropriedade("encontrarLugarStrategy");


            Class<?> clazz = Class.forName(className);
            Object obj = clazz.getDeclaredConstructor().newInstance();

            if (obj instanceof IEncontrarLugarStrategy) {
                return (IEncontrarLugarStrategy) obj;
            } else {
                return (IEncontrarLugarStrategy) new LugarAleatorioStrategy();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o adaptador de cartões de crédito.", e);
        }
    }
    
}
