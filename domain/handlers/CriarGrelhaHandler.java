package domain.handlers;

import java.util.Optional;

import domain.api.ICriarGrelhaHandler;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.NonUniqueException;
import domain.core.lugares.CatalogoGrelhas;
import domain.core.lugares.CatalogoTiposDeLugar;
import domain.core.lugares.Grelha;
import domain.core.lugares.TipoDeLugar;

public class CriarGrelhaHandler implements ICriarGrelhaHandler {

	private CatalogoTiposDeLugar catTipos;
	private CatalogoGrelhas catGrelhas;
	private Grelha g;
	
	public CriarGrelhaHandler(CatalogoGrelhas catGrelhas, CatalogoTiposDeLugar catTipos) {
		this.catGrelhas = catGrelhas;
		this.catTipos = catTipos;
	}

	/**
     * Inicia a criação de uma nova grelha com designação e índice de preço.
     * 
     * @param desig Designação da grelha.
     * @param ind Índice de preço.
     * @throws NonUniqueException Se já existir uma grelha com a mesma designação.
     */
	@Override
	public void iniciarGrelha(String desig, double ind) throws NonUniqueException {
	    if (catGrelhas.existeGrelha(desig)) {
	        throw new NonUniqueException("Já existe uma grelha com a designação: " + desig);
	    }
	    g = new Grelha(desig, ind);
	}

	 /**
     * Define as dimensões da grelha (altura e largura) e cria os lugares,
     * usando o tipo padrão, se disponível.
     * 
     * @param alt Altura da grelha.
     * @param larg Largura da grelha.
     * @return Optional vazio (sem mensagem de erro).
     */
	@Override
	public Optional<String> indicarDimensao(int alt, int larg) {
		Optional<TipoDeLugar> padr = catTipos.getPadrao();
		g.criaLugares(alt, larg, padr);
		return Optional.empty();
	}

	 /**
     * Define o tipo de lugar padrão da grelha.
     * 
     * @param tip Designação do tipo de lugar.
     * @throws DoesNotExistException Se o tipo de lugar não existir no catálogo.
     */
	@Override
	public void indicarTipoPadrao(String tip) throws DoesNotExistException {
	    Optional<TipoDeLugar> tp = catTipos.getTipo(tip);
	    
	    if (tp.isEmpty()) {
	        throw new DoesNotExistException("Tipo de lugar '" + tip + "' não existe.");
	    }

	    g.defineTipoLugarPadrao(tp);
	}

	   /**
     * Define o tipo de lugar numa posição específica da grelha.
     * 
     * @param i Índice da linha.
     * @param j Índice da coluna.
     * @param tip Designação do tipo de lugar.
     * @throws DoesNotExistException Se o tipo de lugar não existir ou as coordenadas forem inválidas.
     */
	@Override
	public void indicarTipoLugar(int i, int j, String tip) throws DoesNotExistException {
		boolean b = g.coordenadasValidas(i, j);
		Optional<TipoDeLugar> tp = catTipos.getTipo(tip);
		
		if (tp.isEmpty() || !b) {
	        throw new DoesNotExistException("Tipo de lugar '" + tip + "' não existe.");
	    }

	}

	 /**
     * Finaliza a criação da grelha, adicionando-a ao catálogo de grelhas.
     */
	@Override
	public void terminar() {
		catGrelhas.acrescentaGrelha(g);

	}

}
