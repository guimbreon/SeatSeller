package domain.core.lugares;

import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.time.LocalTime;
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
	
    /**
     * Construtor da grelha.
     *
     * @param desig a designação da grelha
     * @param indicePreco o índice de preço que multiplica o custo dos lugares
     */
    public Grelha(String desig, double indicePreco) {
        this.desig = desig;
        this.indicePreco = indicePreco;
    }
    
    /**
     * Regista um funcionário como observador das notificações da grelha.
     *
     * @param f o funcionário a adicionar
     */
    public void addObserver(Funcionario f) {
        support.addPropertyChangeListener(f);
    }

    /**
     * Remove um funcionário da lista de observadores da grelha.
     *
     * @param f o funcionário a remover
     */
    public void deleteObserver(Funcionario f) {
        support.removePropertyChangeListener(f);
    }
    
    /**
     * Notifica os observadores (funcionários) sobre uma reserva confirmada.
     *
     * @param lug o lugar reservado
     */
    public void notificar(Lugar lug) {
    	Map<String, Object> info = new HashMap<String, Object>();
        info.put("grelha", getDesignacao());
        info.put("lugar", lug.toString());
        support.firePropertyChange("reservaConfirmada", null, info);
    }
    
    /**
     * Obtém a designação da grelha.
     *
     * @return a designação
     */
    public String getDesignacao() {
        return desig;
    }

    /**
     * Obtém o índice de preço da grelha.
     *
     * @return o índice
     */
    public double getIndice() {
        return indicePreco;
    }

    /**
     * Retorna uma cópia da lista de lugares da grelha.
     *
     * @return lista de lugares
     */
    public List<Lugar> getLugares() {
        return new ArrayList<Lugar>(lugares);
    }
    
    /**
     * Procura um lugar na grelha por coordenadas.
     *
     * @param linha a linha do lugar
     * @param coluna a coluna do lugar
     * @return o lugar, se encontrado
     */
    public Optional<Lugar> getLugar(int linha, int coluna) {
        return lugares.stream()
                      .filter(l -> l.getLinha() == linha && l.getColuna() == coluna)
                      .findFirst();
    }
 
    /**
     * Obtém todas as combinações de lugares disponíveis para uma data e hora específicas.
     * Cada combinação representa um tipo de lugar, seu preço e disponibilidade.
     *
     * @param date a data desejada
     * @param time a hora desejada
     * @return lista de combinações disponíveis
     */
    public List<Combinacao> getCombinacoes(LocalDate date, LocalTime time) {
        combinacoes = new ArrayList<Combinacao>();  // lista onde vamos acumular as combinações

        lugares.stream()
            .filter(lug -> lug.disponivel(date, time))
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

    /**
     * Verifica se já existe uma combinação com o mesmo tipo de lugar.
     * Se existir, incrementa a sua disponibilidade.
     *
     * @param tp o tipo de lugar
     * @return a combinação existente ou null
     */
    private Combinacao verificaTipoIgual(String tp) {
        for (Combinacao c : combinacoes) {
            if (c.getTipoDeLugar().equals(tp)) {
                c.aumentaDisponibilidade();  // só incrementa o contador interno
                return c;
            }
        }
        return null;
    }
    
    /**
     * Cria a estrutura da grelha com lugares em linhas e colunas.
     *
     * @param alt número de linhas
     * @param larg número de colunas
     * @param padr tipo de lugar padrão (opcional)
     */
    public void criaLugares(double alt, double larg, Optional<TipoDeLugar> padr) {
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < larg; j++) {
                TipoDeLugar tipo = padr.isPresent() ? padr.get() : null;
                Lugar l = new Lugar(i, j, tipo, this);
                lugares.add(l);
            }
        }
    }
    
    /**
     * Obtém um lugar disponível que corresponda ao tipo desejado, se existir,
     * usando a estratégia de alocação definida.
     *
     * @param t tipo de lugar desejado (opcional)
     * @param data a data da reserva
     * @param hora a hora da reserva
     * @return lugar disponível (se houver)
     */
    public Optional<Lugar> getDisponivel(Optional<TipoDeLugar> t, LocalDate data, LocalTime hora) {
    	IEncontrarLugarStrategy strat = EncontrarLugarStrategyFactory.getInstance().getEncontrarLugarStrategy();
        return strat.getLugar(this, t, data, hora);	
    }
    
    /**
     * Verifica se as coordenadas informadas correspondem a um lugar existente na grelha.
     *
     * @param i linha
     * @param j coluna
     * @return true se as coordenadas forem válidas
     */
    public boolean coordenadasValidas(int i, int j) {
        return lugares.stream()
                .anyMatch(l -> l.getLinha() == i && l.getColuna() == j);
    }

    /**
     * Define o tipo de um lugar específico, se ele existir.
     *
     * @param i linha do lugar
     * @param j coluna do lugar
     * @param tp tipo de lugar a atribuir (opcional)
     */
    public void defineTipoLugar(int i, int j, Optional<TipoDeLugar> tp) {
    	lugares.stream()
        .filter(lugar -> lugar.getLinha() == i && lugar.getColuna() == j)
        .findFirst()
        .ifPresent(lugar -> lugar.definirTipo(tp));
    }
    
    /**
     * Define o tipo de lugar padrão para todos os lugares da grelha.
     *
     * @param tp tipo padrão a definir (opcional)
     */
    public void defineTipoLugarPadrao( Optional<TipoDeLugar> tp) {
    	lugares.stream()
        .forEach(lugar -> lugar.definirTipo(tp));
    }
}
