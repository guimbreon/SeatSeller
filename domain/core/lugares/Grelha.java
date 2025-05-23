package domain.core.lugares;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Grelha {

    private String desig;
    private double indicePreco;
    private List<Lugar> lugares = new ArrayList<Lugar>();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
	
    public Grelha(String desig, double indicePreco) {
        this.desig = desig;
        this.indicePreco = indicePreco;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
    
    public String getDesig() {
        return desig;
    }

    public double getIndicePreco() {
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
    
    public Lugar criaLugares(double alt, double larg, Optional<TipoDeLugar> padr) {
        Lugar ultimo = null;

        for (int i = 1; i <= alt; i++) {
            for (int j = 1; j <= larg; j++) {
                TipoDeLugar tipo = padr.isPresent() ? padr.get() : null;
                Lugar l = new Lugar(i, j, tipo, this);
                lugares.add(l);
                ultimo = l;
            }
        }

        return ultimo;	
    }
    
	
}
