package domain.core.lugares.alocacao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;

public class LugarMaisAfastadoStrategy implements IEncontrarLugarStrategy {

	public Optional<Lugar> getLugar(Grelha grelha, TipoDeLugar tp, LocalDate data, LocalTime hora) {
		return grelha.getLugares().stream()
	            .filter(l -> l.getDesignacaoTipo() != null)
	            .filter(l -> l.getDesignacaoTipo().equals(tp.getDesig()))
	            .filter(l -> l.disponivel(data, hora))
	            .max((l1, l2) -> {
	                if (l1.getLinha() != l2.getLinha()) {
	                    return Integer.compare(l1.getLinha(), l2.getLinha());
	                }
	                return Integer.compare(l1.getColuna(), l2.getColuna());
	            });
	}
	
}
