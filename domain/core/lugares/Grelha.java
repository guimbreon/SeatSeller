package domain.core.lugares;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import domain.api.wrappers.Combinacao;

public class Grelha {

    private String desig;
    private double indicePreco;
    private List<Lugar> lugares = new ArrayList<Lugar>();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private List<Combinacao> combinacoes;
	
    public Grelha(String desig, double indicePreco) {
        this.desig = desig;
        this.indicePreco = indicePreco;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }//é preciso?

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }//é preciso??
    
    public void notificar(Lugar lug) {
    	Map<String, Object> info = new HashMap<>();
        info.put("grelha", this);
        info.put("lugar", lug);
        support.firePropertyChange("reservaConfirmada", null, lug);
    }
    
    public String getDesignacao() {
        return desig;
    }

    public double getIndice() {
        return indicePreco;
    }

    public List<Lugar> getLugares() {
        return new ArrayList<Lugar>(lugares);
    }
    
    public Optional<Lugar> getLugar(int linha, int coluna) {
        return lugares.stream()
                      .filter(l -> l.getLinha() == linha && l.getColuna() == coluna)
                      .findFirst();
    }
    //ver os sets que é preciso  
    
    public List<Combinacao> getCombinacoes(String data, String hora) {
        combinacoes = new ArrayList<Combinacao>();  // lista onde vamos acumular as combinações

        lugares.stream()
            .filter(lug -> lug.disponivel(data, hora))
            .forEach(lug -> {
                String tp = lug.getDesignacaoTipo();

                // Tenta encontrar uma Combinacao existente do mesmo tipo e incrementa a disponibilidade
                Combinacao existente = verificaTipoIgual(tp);

                // Se não encontrar, cria uma nova e adiciona à lista
                if (existente == null) {
                    Combinacao nova = new Combinacao(getDesignacao(), tp, lug.getPreco());
                    combinacoes.add(nova);
                }
                // Se existir, a disponibilidade já foi incrementada dentro de verificaTipoIgual
            });

        return new ArrayList<Combinacao>(combinacoes);
    }

    private Combinacao verificaTipoIgual(String tp) {
        for (Combinacao c : combinacoes) {
            if (c.getTipoDeLugar().equals(tp)) {
                c.aumentaDisponibilidade();  // só incrementa o contador interno
                return c;
            }
        }
        return null;
    }
    
    
    public void criaLugares(double alt, double larg, Optional<TipoDeLugar> padr) {

        for (int i = 1; i <= alt; i++) {
            for (int j = 1; j <= larg; j++) {
                TipoDeLugar tipo = padr.isPresent() ? padr.get() : null;
                Lugar l = new Lugar(i, j, tipo, this);
                lugares.add(l);
            }
        }

    }
    
    public Lugar getDisponivel(Optional<TipoDeLugar> t, String data, String hora) {
        	
    }
    
    public boolean coordenadasValidas(double i, double j) {
    	
    }
    
    public void defineTipoLugar(double i, double j, Optional<TipoDeLugar> tp) {
    	
    }
}
