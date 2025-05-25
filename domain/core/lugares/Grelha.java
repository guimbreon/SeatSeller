package domain.core.lugares;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import domain.api.wrappers.Combinacao;
import domain.core.lugares.alocacao.EncontrarLugarStrategyFactory;
import domain.core.lugares.alocacao.IEncontrarLugarStrategy;
import domain.core.utilizadores.Funcionario;

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
    
    public void addObserver(Funcionario f) {
        support.addPropertyChangeListener(f);
    }

    public void deleteObserver(Funcionario f) {
        support.removePropertyChangeListener(f);
    }
    
    
    public void notificar(Lugar lug) {
    	Map<String, Object> info = new HashMap<String, Object>();
        info.put("grelha", getDesignacao());
        info.put("lugar", lug.toString());
        support.firePropertyChange("reservaConfirmada", null, info);
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
    
    public Optional<Lugar> getDisponivel(TipoDeLugar t, String data, String hora) {
    	IEncontrarLugarStrategy strat = EncontrarLugarStrategyFactory.getInstance().getEncontrarLugarStrategy();
        return strat.getLugar(this, t, data, hora);	
    }
    
    public boolean coordenadasValidas(double i, double j) {
    	return lugares.stream()
                .anyMatch(l -> l.getLinha() == (int)i && l.getColuna() == (int)j);
    }
    
    public void defineTipoLugar(int i, int j, Optional<TipoDeLugar> tp) {
    	lugares.stream()
        .filter(lugar -> lugar.getLinha() == i && lugar.getColuna() == j)
        .findFirst()
        .ifPresent(lugar -> lugar.definirTipo(tp));
    }
    
    public void defineTipoLugarPadrao( Optional<TipoDeLugar> tp) {
    	lugares.stream()
        .forEach(lugar -> lugar.definirTipo(tp));
    }
}
