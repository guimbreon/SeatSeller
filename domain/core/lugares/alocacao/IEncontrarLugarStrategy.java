package domain.core.lugares.alocacao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;

public interface IEncontrarLugarStrategy {
	
    Optional<Lugar> getLugar(Grelha grelha, TipoDeLugar tipo, LocalDate data, LocalTime hora);
}
