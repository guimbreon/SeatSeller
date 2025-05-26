package domain.core.lugares.alocacao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;

public class LugarMaisAfastadoStrategy implements IEncontrarLugarStrategy {

    /**
     * Procura o lugar disponível com a maior linha (e maior coluna em caso de empate),
     * que esteja livre na data e hora especificadas, e que corresponda ao tipo de lugar
     * desejado, se especificado.
     *
     * @param grelha a grelha onde a procura será feita
     * @param tp o tipo de lugar desejado (opcional)
     * @param data a data para verificar disponibilidade
     * @param hora a hora para verificar disponibilidade
     * @return um {@link Optional} com o lugar mais afastado disponível ou vazio se não houver
     */
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
