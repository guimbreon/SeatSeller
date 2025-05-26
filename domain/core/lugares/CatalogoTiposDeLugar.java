package domain.core.lugares;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CatalogoTiposDeLugar {
	private Map<String, TipoDeLugar> tipos = new HashMap<>();
	private Optional<TipoDeLugar> tipoPadrao = Optional.empty();
	
	 /**
     * Cria e adiciona um novo tipo de lugar ao catálogo.
     * Pode opcionalmente defini-lo como tipo padrão.
     *
     * @param desig a designação (nome curto) do tipo de lugar
     * @param desc uma descrição mais detalhada do tipo
     * @param preco o preço base associado ao tipo
     * @param padrao true se este tipo deve ser definido como o tipo padrão
     */
	public void criarTipoDeLugar(String desig, String desc, double preco, boolean padrao) {
		TipoDeLugar t = new TipoDeLugar(desig, desc, preco);
		tipos.put(desig, t);
		if (padrao) tipoPadrao = Optional.ofNullable(t);
	}

	 /**
     * Retorna o tipo de lugar padrão, se houver.
     *
     * @return um Optional com o tipo de lugar padrão
     */
	public Optional<TipoDeLugar> getPadrao() {
		return tipoPadrao;
	}

	 /**
     * Obtém um tipo de lugar específico a partir da sua designação.
     *
     * @param desig a designação do tipo de lugar
     * @return um Optional com o tipo de lugar correspondente, se existir
     */
	public Optional<TipoDeLugar> getTipo(String desig) {
		return Optional.ofNullable(tipos.get(desig));
	}


}
