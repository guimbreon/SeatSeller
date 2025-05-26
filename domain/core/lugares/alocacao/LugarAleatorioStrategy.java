package domain.core.lugares.alocacao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;

public class LugarAleatorioStrategy implements IEncontrarLugarStrategy {

	 /**
     * Retorna aleatoriamente um {@link Lugar} que esteja disponível na data e hora
     * especificadas, e que corresponda ao tipo de lugar desejado, caso fornecido.
     *
     * @param grelha a grelha na qual procurar o lugar
     * @param t o tipo de lugar desejado (opcional)
     * @param data a data para a qual verificar disponibilidade
     * @param hora a hora para a qual verificar disponibilidade
     * @return um {@link Optional} com um lugar aleatório disponível ou vazio se não houver
     */
	public Optional<Lugar> getLugar(Grelha grelha, Optional<TipoDeLugar> t, LocalDate data, LocalTime hora) {
		
        String tipoDesejado = t.map(TipoDeLugar::getDesig).orElse(null);

        List<Lugar> candidatos = grelha.getLugares().stream()
            .filter(l -> l.disponivel(data, hora))
            .filter(l -> tipoDesejado == null || l.getDesignacaoTipo().equals(tipoDesejado))
            .collect(Collectors.toList());

        Collections.shuffle(candidatos);
        return candidatos.stream().findFirst();
	}

	
	
}
