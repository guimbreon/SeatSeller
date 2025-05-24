package domain.cartoesdecredito;

import domain.Configuration;

public class SistemaDeCartoesDeCreditoAdapterFactory {
    private static SistemaDeCartoesDeCreditoAdapterFactory INSTANCE = new SistemaDeCartoesDeCreditoAdapterFactory();

    private SistemaDeCartoesDeCreditoAdapterFactory() {
        // Construtor privado para o padrão Singleton
    }
    
    
    public static SistemaDeCartoesDeCreditoAdapterFactory getInstance() {
        return INSTANCE;
    }
    
    
    public ISistemaDeCartoesDeCreditoAdapter getSistemaDeCartoesDeCreditoAdapter() {
        try {
            String className = Configuration.getInstance().valorPropriedade("cartaoAdapter");


            Class<?> clazz = Class.forName(className);
            Object obj = clazz.getDeclaredConstructor().newInstance();

            if (obj instanceof ISistemaDeCartoesDeCreditoAdapter) {
                return (ISistemaDeCartoesDeCreditoAdapter) obj;
            } else {
                return (ISistemaDeCartoesDeCreditoAdapter) new SistemaDeCartoesDeCreditoDummy();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o adaptador de cartões de crédito.", e);
        }
    }
}
