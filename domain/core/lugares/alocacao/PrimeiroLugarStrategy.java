package domain.core.lugares.alocacao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;

public class PrimeiroLugarStrategy implements IEncontrarLugarStrategy {

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
