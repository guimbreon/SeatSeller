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
