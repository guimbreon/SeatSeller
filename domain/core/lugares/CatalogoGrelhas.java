package domain.core.lugares;

import java.util.HashMap;
import java.util.Map;

import domain.api.wrappers.Combinacao;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CatalogoGrelhas {

    private Map<String, Grelha> grelhas = new HashMap<>();

    /**
     * Obtém a grelha com a designação fornecida.
     *
     * @param desig a designação da grelha
     * @return a grelha correspondente ou null se não existir
     */
    public Grelha getGrelha(String desig) {
        return grelhas.get(desig);
    }

    /**
     * Obtém uma lista de combinações (grelha + tipo de lugar + preço + disponibilidade)
     * disponíveis para a data e hora fornecidas, agregadas de todas as grelhas.
     *
     * @param date a data da sessão
     * @param time a hora da sessão
     * @return lista de combinações disponíveis
     */
    public List<Combinacao> getCombinacoes(LocalDate date, LocalTime time) {
        List<Combinacao> comb = new ArrayList<>();

        for (Grelha g : grelhas.values()) {
            // Assuming getCombinacoes returns a List<Combinacao> or a single Combinacao
            List<Combinacao> c = g.getCombinacoes(date, time);  // Adjust this if it's a list
            if (c != null) {
                for (Combinacao combItem : c) {
                    comb.add(combItem);
                }
            }
        }

        return comb;
    }
    
    /**
     * Verifica se existe uma grelha com a designação fornecida.
     *
     * @param desig a designação da grelha
     * @return true se a grelha existir, false caso contrário
     */
    public boolean existeGrelha(String desig) {
        return grelhas.containsKey(desig);
    }
    
    
    /**
     * Acrescenta uma nova grelha ao catálogo.
     *
     * @param g a grelha a adicionar
     */
    public void acrescentaGrelha(Grelha g) {
        grelhas.put(g.getDesignacao(), g);
    }
}
