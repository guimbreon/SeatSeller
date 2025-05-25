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

    public Grelha getGrelha(String desig) {
        return grelhas.get(desig);
    }

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
    
    public boolean existeGrelha(String desig) {
        return grelhas.containsKey(desig);
    }
    
    public void acrescentaGrelha(Grelha g) {
        grelhas.put(g.getDesignacao(), g);
    }
}
