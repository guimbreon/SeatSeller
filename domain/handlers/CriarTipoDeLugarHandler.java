package domain.handlers;

import domain.api.ICriarTipoDeLugarHandler;
import domain.core.lugares.CatalogoTiposDeLugar;

public class CriarTipoDeLugarHandler implements ICriarTipoDeLugarHandler {
	
	private CatalogoTiposDeLugar catTipos;
	
	public CriarTipoDeLugarHandler(CatalogoTiposDeLugar catTipos) {
		this.catTipos = catTipos;
	}
	
	@Override
	public void criarTipoDeLugar(String desig, String desc, double preco, boolean padrao) {
		catTipos.criarTipoDeLugar(desig, desc, preco, padrao);
	}
}
