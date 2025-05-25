package domain.core.lugares.alocacao;

import java.util.Optional;

import domain.core.lugares.Grelha;
import domain.core.lugares.Lugar;
import domain.core.lugares.TipoDeLugar;

public interface IEncontrarLugarStrategy {
	
    Optional<Lugar> getLugar(Grelha grelha, TipoDeLugar tipo, String data, String hora);
}
