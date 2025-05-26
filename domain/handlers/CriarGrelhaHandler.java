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

	@Override
	public void iniciarGrelha(String desig, double ind) throws NonUniqueException {
	    if (catGrelhas.existeGrelha(desig)) {
	        throw new NonUniqueException("Já existe uma grelha com a designação: " + desig);
	    }
	    g = new Grelha(desig, ind);
	}

	@Override
	public Optional<String> indicarDimensao(int alt, int larg) {
		Optional<TipoDeLugar> padr = catTipos.getPadrao();
		g.criaLugares(alt, larg, padr);
		return Optional.empty();
	}

	@Override
	public void indicarTipoPadrao(String tip) throws DoesNotExistException {
	    Optional<TipoDeLugar> tp = catTipos.getTipo(tip);
	    
	    if (tp.isEmpty()) {
	        throw new DoesNotExistException("Tipo de lugar '" + tip + "' não existe.");
	    }

	    g.defineTipoLugarPadrao(tp);
	}

	@Override
	public void indicarTipoLugar(int i, int j, String tip) throws DoesNotExistException {
		boolean b = g.coordenadasValidas(i, j);
		Optional<TipoDeLugar> tp = catTipos.getTipo(tip);
		
		if (tp.isEmpty() || !b) {
	        throw new DoesNotExistException("Tipo de lugar '" + tip + "' não existe.");
	    }

	}

	@Override
	public void terminar() {
		catGrelhas.acrescentaGrelha(g);

	}

}
