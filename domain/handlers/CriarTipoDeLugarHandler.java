package domain.handlers;

import java.util.Map;

import domain.api.ICriarTipoDeLugarHandler;
import domain.core.lugares.CatalogoTiposDeLugar;
import domain.core.lugares.TipoDeLugar;

public class CriarTipoDeLugarHandler implements ICriarTipoDeLugarHandler {
	
	private CatalogoTiposDeLugar catTipos;
	
	public CriarTipoDeLugarHandler(CatalogoTiposDeLugar catTipos) {
		this.catTipos = catTipos;
	}
	
    /**
     * Cria um novo tipo de lugar no catálogo com os dados fornecidos.
     * 
     * @param desig Designação do tipo de lugar.
     * @param desc Descrição detalhada do tipo de lugar.
     * @param preco Preço associado a este tipo de lugar.
     * @param padrao Indica se este tipo é o tipo padrão.
     */
	@Override
	public void criarTipoDeLugar(String desig, String desc, double preco, boolean padrao) {
		catTipos.criarTipoDeLugar(desig, desc, preco, padrao);
	}
	
}
