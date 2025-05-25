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
		List<Lugar> candidatos = grelha.getLugares().stream()
	            .filter(l -> l.getDesignacaoTipo() != null)
	            .filter(l -> l.getDesignacaoTipo().equals(tp.getDesig()))
	            .filter(l -> l.disponivel(data, hora))
	            .collect(Collectors.toList());

	        Collections.shuffle(candidatos);

	        return candidatos.stream().findFirst();
	}

	
	
}
