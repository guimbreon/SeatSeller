package domain.core.lugares.alocacao;

import java.util.Optional;

import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;

public class PrimeiroLugarStrategy implements IEncontrarLugarStrategy {

	public Optional<Lugar> getLugar(Grelha grelha, TipoDeLugar tp, String data, String hora) {
		return grelha.getLugares().stream()
	            .filter(lugar -> lugar.getDesignacaoTipo() != null)
	            .filter(lugar -> lugar.getDesignacaoTipo().equals(tp.getDesig()))
	            .filter(lugar -> lugar.disponivel(data, hora))
	            .findFirst();
	}	
}
