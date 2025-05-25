package domain.core.lugares.alocacao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;

public class LugarMaisAfastadoStrategy implements IEncontrarLugarStrategy {

	 @Override
     public Optional<Lugar> getLugar(Grelha grelha, Optional<TipoDeLugar> tp, LocalDate data, LocalTime hora) {
        Optional<String> tipoDesejado = tp.map(TipoDeLugar::getDesig);

        return grelha.getLugares().stream()
            .filter(l -> tipoDesejado
                .map(desig -> desig.equals(l.getDesignacaoTipo()))
                .orElse(true))
            .filter(l -> l.disponivel(data, hora))
            .max((l1, l2) -> {
                if (l1.getLinha() != l2.getLinha()) {
                    return Integer.compare(l1.getLinha(), l2.getLinha());
                }
                return Integer.compare(l1.getColuna(), l2.getColuna());
            });
     }
	
}
