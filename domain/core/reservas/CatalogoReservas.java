package domain.core.reservas;

import java.util.HashMap;
import java.util.Map;

public class CatalogoReservas {

    private Map<String, Reserva> reservas;

    public CatalogoReservas() {
        this.reservas = new HashMap<>();
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.put(reserva.getCodigo(), reserva);
    }

    public Reserva getReserva(String codRes) {
        return reservas.get(codRes);
    }
}
