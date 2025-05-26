package domain.core.lugares.alocacao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;

public class PrimeiroLugarStrategy implements IEncontrarLugarStrategy {

	/**
     * Retorna o primeiro {@link Lugar} disponível na {@link Grelha} que:
     * <ul>
     *   <li>Esteja livre na data e hora especificadas</li>
     *   <li>Seja do tipo desejado, caso especificado</li>
     * </ul>
     *
     * @param grelha a grelha onde procurar
     * @param t o tipo de lugar desejado, se houver
     * @param data a data para a qual verificar disponibilidade
     * @param hora a hora para a qual verificar disponibilidade
     * @return um Optional contendo o primeiro lugar disponível que satisfaça os critérios, ou vazio se não houver
     */
	@Override
	public Optional<Lugar> getLugar(Grelha grelha, Optional<TipoDeLugar> t, LocalDate data, LocalTime hora) {
		Optional<String> tipoDesejado = t.map(TipoDeLugar::getDesig);

	    return grelha.getLugares().stream()
	        .filter(lugar -> lugar.getDesignacaoTipo() != null)
	        .filter(lugar -> tipoDesejado
	            .map(desig -> desig.equals(lugar.getDesignacaoTipo()))
	            .orElse(true))
	        .filter(lugar -> lugar.disponivel(data, hora))
	        .findFirst();
	}
}
