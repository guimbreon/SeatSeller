package domain.core.lugares.alocacao;

import domain.Configuration;



public class EncontrarLugarStrategyFactory {
	private static EncontrarLugarStrategyFactory INSTANCE = new EncontrarLugarStrategyFactory();
	
	private EncontrarLugarStrategyFactory() {
		// Padrão Singleton
	}
	
	/**
     * Retorna a instância única da fábrica.
     *
     * @return instância singleton de {@code EncontrarLugarStrategyFactory}
     */
    public static EncontrarLugarStrategyFactory getInstance() {
        return INSTANCE;
    }
	
    /**
     * Retorna uma instância da estratégia de alocação de lugares especificada na configuração.
     * Se a classe não puder ser carregada ou não implementar a interface {@link IEncontrarLugarStrategy},
     * retorna a estratégia padrão {@link LugarAleatorioStrategy}.
     *
     * @return uma instância de {@link IEncontrarLugarStrategy}
     * @throws RuntimeException caso ocorra erro durante a criação da instância
     */
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
